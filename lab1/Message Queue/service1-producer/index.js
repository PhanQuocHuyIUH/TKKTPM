const amqp = require("amqplib");

const RABBITMQ_URL =
  process.env.RABBITMQ_URL || "amqp://admin:admin123@localhost:5672";
const QUEUE_NAME = "event_queue";

async function publishEvent() {
  try {
    // Kết nối tới RabbitMQ
    const connection = await amqp.connect(RABBITMQ_URL);
    const channel = await connection.createChannel();

    // Đảm bảo queue tồn tại
    const queueInfo = await channel.assertQueue(QUEUE_NAME, {
      durable: true, // Queue sẽ tồn tại khi RabbitMQ restart
    });

    console.log("🚀 [Service 1] Producer đã khởi động và đang push events...");
    console.log("📊 Thông tin RabbitMQ:");
    console.log(`   - URL: ${RABBITMQ_URL}`);
    console.log(`   - Queue Name: ${QUEUE_NAME}`);
    console.log(`   - Queue Durable: true`);
    console.log(`   - Message Persistent: true`);
    console.log(`   - Interval: 10 giây\n`);

    // Tạo event counter
    let eventCounter = 1;

    // Push event mỗi 10 giây
    setInterval(async () => {
      const event = {
        id: eventCounter,
        message: `Event ${eventCounter}`,
        timestamp: new Date().toISOString(),
        data: {
          status: "active",
          priority: Math.floor(Math.random() * 5) + 1,
        },
      };

      channel.sendToQueue(QUEUE_NAME, Buffer.from(JSON.stringify(event)), {
        persistent: true, // Message sẽ được lưu vào disk
      });

      // Lấy thông tin queue
      const checkQueue = await channel.checkQueue(QUEUE_NAME);

      console.log(
        `✅ [Service 1] Đã push event #${eventCounter}:`,
        event.message,
      );
      console.log(`   📦 Queue Stats:`);
      console.log(`      - Messages in Queue: ${checkQueue.messageCount}`);
      console.log(`      - Consumers: ${checkQueue.consumerCount}`);
      console.log(`      - Timestamp: ${event.timestamp}\n`);

      eventCounter++;
    }, 10000);
  } catch (error) {
    console.error("❌ [Service 1] Lỗi:", error);
    process.exit(1);
  }
}

// Xử lý graceful shutdown
process.on("SIGINT", () => {
  console.log("\n⛔ [Service 1] Đang dừng producer...");
  process.exit(0);
});

// Khởi động producer
publishEvent();
