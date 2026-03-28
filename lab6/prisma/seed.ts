import "dotenv/config";
import { PrismaClient } from "@prisma/client";
import { PrismaPg } from "@prisma/adapter-pg";

const prisma = new PrismaClient({
  adapter: new PrismaPg({ connectionString: process.env.DATABASE_URL }),
});

const users = [
  { name: "Nguyen Van A", email: "nguyenvana@email.com", phone: "0901234567" },
  { name: "Tran Thi B", email: "tranthib@email.com", phone: "0912345678" },
  { name: "Le Van C", email: "levanc@email.com", phone: "0923456789" },
  { name: "Pham Thi D", email: "phamthid@email.com" },
  { name: "Hoang Van E", email: "hoangvane@email.com", phone: "0945678901" },
];

const products = [
  { name: "Laptop", description: "High-performance laptop with 16GB RAM", price: 999.99, category: "Electronics", quantity: 50 },
  { name: "Wireless Mouse", description: "Ergonomic wireless mouse", price: 29.99, category: "Electronics", quantity: 200 },
  { name: "Mechanical Keyboard", description: "RGB mechanical keyboard with Cherry MX switches", price: 89.99, category: "Electronics", quantity: 150 },
  { name: "Monitor 27 inch", description: "4K UHD IPS monitor", price: 349.99, category: "Electronics", quantity: 75 },
  { name: "USB-C Hub", description: "7-in-1 USB-C multiport adapter", price: 45.99, category: "Accessories", quantity: 300 },
  { name: "Webcam HD", description: "1080p HD webcam with microphone", price: 59.99, category: "Accessories", quantity: 120 },
  { name: "Desk Lamp", description: "LED desk lamp with adjustable brightness", price: 24.99, category: "Office", quantity: 180 },
  { name: "Office Chair", description: "Ergonomic office chair with lumbar support", price: 249.99, category: "Furniture", quantity: 40 },
  { name: "Standing Desk", description: "Electric height-adjustable standing desk", price: 499.99, category: "Furniture", quantity: 25 },
  { name: "Notebook Pack", description: "Pack of 5 lined notebooks", price: 12.99, category: "Office", quantity: 500 },
];

async function main() {
  console.log("Seeding database...");

  // Seed users
  const createdUsers = [];
  for (const user of users) {
    const created = await prisma.user.create({ data: user });
    createdUsers.push(created);
  }
  console.log(`Seeded ${createdUsers.length} users.`);

  // Seed products
  const createdProducts = [];
  for (const product of products) {
    const created = await prisma.product.create({ data: product });
    createdProducts.push(created);
  }
  console.log(`Seeded ${createdProducts.length} products.`);

  // Seed orders
  const orders = [
    {
      userId: createdUsers[0].id,
      items: [
        { productId: createdProducts[0].id, quantity: 1, price: createdProducts[0].price },
        { productId: createdProducts[2].id, quantity: 2, price: createdProducts[2].price },
      ],
    },
    {
      userId: createdUsers[1].id,
      items: [
        { productId: createdProducts[3].id, quantity: 1, price: createdProducts[3].price },
      ],
    },
    {
      userId: createdUsers[2].id,
      items: [
        { productId: createdProducts[1].id, quantity: 3, price: createdProducts[1].price },
        { productId: createdProducts[4].id, quantity: 2, price: createdProducts[4].price },
        { productId: createdProducts[6].id, quantity: 1, price: createdProducts[6].price },
      ],
    },
    {
      userId: createdUsers[0].id,
      items: [
        { productId: createdProducts[7].id, quantity: 1, price: createdProducts[7].price },
        { productId: createdProducts[8].id, quantity: 1, price: createdProducts[8].price },
      ],
    },
    {
      userId: createdUsers[4].id,
      items: [
        { productId: createdProducts[5].id, quantity: 2, price: createdProducts[5].price },
        { productId: createdProducts[9].id, quantity: 5, price: createdProducts[9].price },
      ],
    },
  ];

  for (const order of orders) {
    const totalAmount = order.items.reduce((sum, item) => sum + item.price * item.quantity, 0);
    await prisma.order.create({
      data: {
        userId: order.userId,
        totalAmount,
        status: "completed",
        items: { create: order.items },
      },
    });
  }
  console.log(`Seeded ${orders.length} orders.`);
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
