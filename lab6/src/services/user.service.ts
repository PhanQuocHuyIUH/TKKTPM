import prisma from "../prisma";

export class UserService {
  async createUser(data: { name: string; email: string; phone?: string }) {
    return prisma.user.create({ data });
  }

  async getAllUsers() {
    return prisma.user.findMany({
      orderBy: { createdAt: "desc" },
      include: { _count: { select: { orders: true } } },
    });
  }

  async getUserById(id: number) {
    return prisma.user.findUnique({
      where: { id },
      include: { orders: true },
    });
  }
}

export default new UserService();
