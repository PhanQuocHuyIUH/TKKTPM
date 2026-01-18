const amqp = require("amqplib");

const RABBITMQ_URL =
  process.env.RABBITMQ_URL || "amqp://admin:admin123@localhost:5672";
const QUEUE_NAME = "event_queue";

async function consumeEvents() {
  try {
    // Kết nối tới RabbitMQ
    const connection = await amqp.connect(RABBITMQ_URL);
    const channel = await connection.createChannel();

    // Đảm bảo queue tồn tại
    await channel.assertQueue(QUEUE_NAME, {
      durable: true,
    });

    // Giới hạn số message được xử lý đồng thời
    channel.prefetch(1);

    console.log("🚀 [Service 2] Consumer đã khởi động và đang chờ events...\n");

    // Consume messages từ queue
    channel.consume(
      QUEUE_NAME,
      async (message) => {
        if (message !== null) {
          try {
            const event = JSON.parse(message.content.toString());

            console.log(`📨 [Service 2] Nhận được event #${event.id}`);
            console.log(`   Message: ${event.message}`);
            console.log(`   Timestamp: ${event.timestamp}`);
            console.log(`   Priority: ${event.data.priority}`);

            // Giả lập xử lý event (mất 1-2 giây)
            const processingTime = Math.floor(Math.random() * 1000) + 1000;
            await new Promise((resolve) => setTimeout(resolve, processingTime));

            console.log(
              `✅ [Service 2] Đã xử lý event #${event.id} (${processingTime}ms)\n`,
            );

            // Acknowledge message sau khi xử lý thành công
            channel.ack(message);
          } catch (error) {
            console.error("❌ [Service 2] Lỗi xử lý event:", error);
            // Reject message và không requeue
            channel.nack(message, false, false);
          }
        }
      },
      {
        noAck: false, // Yêu cầu manual acknowledgment
      },
    );

    // Xử lý graceful shutdown
    process.on("SIGINT", async () => {
      console.log("\n⛔ [Service 2] Đang dừng consumer...");
      await channel.close();
      await connection.close();
      process.exit(0);
    });
  } catch (error) {
    console.error("❌ [Service 2] Lỗi kết nối:", error);
    // Retry sau 5 giây
    console.log("🔄 [Service 2] Thử kết nối lại sau 5 giây...");
    setTimeout(consumeEvents, 5000);
  }
}

// Khởi động consumer
consumeEvents();
