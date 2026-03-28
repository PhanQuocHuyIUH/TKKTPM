import prisma from "../prisma";

export class OrderService {
  async createOrder(data: {
    userId: number;
    items: { productId: number; quantity: number }[];
  }) {
    return prisma.$transaction(async (tx) => {
      // Calculate total from product prices
      let totalAmount = 0;
      const orderItems = [];

      for (const item of data.items) {
        const product = await tx.product.findUnique({
          where: { id: item.productId },
        });
        if (!product) {
          throw new Error(`Product ${item.productId} not found`);
        }
        if (product.quantity < item.quantity) {
          throw new Error(`Insufficient stock for ${product.name}`);
        }

        totalAmount += product.price * item.quantity;
        orderItems.push({
          productId: item.productId,
          quantity: item.quantity,
          price: product.price,
        });

        // Decrease product stock
        await tx.product.update({
          where: { id: item.productId },
          data: { quantity: { decrement: item.quantity } },
        });
      }

      return tx.order.create({
        data: {
          userId: data.userId,
          totalAmount,
          items: { create: orderItems },
        },
        include: { items: { include: { product: true } }, user: true },
      });
    });
  }

  async getAllOrders() {
    return prisma.order.findMany({
      orderBy: { createdAt: "desc" },
      include: {
        user: { select: { id: true, name: true, email: true } },
        items: { include: { product: { select: { id: true, name: true } } } },
      },
    });
  }

  async getOrderById(id: number) {
    return prisma.order.findUnique({
      where: { id },
      include: {
        user: true,
        items: { include: { product: true } },
      },
    });
  }
}

export default new OrderService();
