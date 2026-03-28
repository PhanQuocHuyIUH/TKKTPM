import prisma from "../prisma";

export class ProductService {
  async createProduct(data: {
    name: string;
    description?: string;
    price: number;
    category: string;
    quantity?: number;
  }) {
    return prisma.product.create({ data });
  }

  async getAllProducts() {
    return prisma.product.findMany({
      orderBy: { createdAt: "desc" },
    });
  }

  async getProductById(id: number) {
    return prisma.product.findUnique({
      where: { id },
    });
  }
}

export default new ProductService();
