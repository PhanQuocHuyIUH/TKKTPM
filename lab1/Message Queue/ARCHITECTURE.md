# Tóm tắt: Cách hoạt động của Message Queue và Docker

## 📋 Tổng quan hệ thống

```
┌─────────────────────────────────────────────────────────────┐
│                    Docker Compose                            │
│  ┌────────────┐    ┌──────────────┐    ┌────────────┐      │
│  │            │    │              │    │            │      │
│  │ RabbitMQ   │◄───┤  Service 1   │    │ Service 2  │      │
│  │  (Broker)  │    │  (Producer)  │    │ (Consumer) │      │
│  │            │───►│              │    │            │      │
│  │  Port 5672 │    │  Push Event  │    │   Process  │      │
│  │ Port 15672 │    │  mỗi 10s     │    │   Event    │      │
│  │            │    │              │    │            │      │
│  └────────────┘    └──────────────┘    └────────────┘      │
│       │                    │                    │           │
│       └────────────────────┴────────────────────┘           │
│              message_queue_network                          │
└─────────────────────────────────────────────────────────────┘
```

## 🔄 Luồng hoạt động chi tiết

### Bước 1: Khởi động hệ thống

```bash
docker-compose up --build -d
```

**Docker Compose thực hiện:**

1. ✅ Tạo Docker network: `message_queue_network`
2. ✅ Tạo volume: `rabbitmq_data` (lưu trữ dữ liệu RabbitMQ)
3. ✅ Build image cho Service 1 từ Dockerfile
4. ✅ Build image cho Service 2 từ Dockerfile
5. ✅ Khởi động RabbitMQ container
6. ✅ Chờ RabbitMQ healthy (health check)
7. ✅ Khởi động Service 1 và Service 2

### Bước 2: RabbitMQ hoạt động

**RabbitMQ Container:**

- Image: `rabbitmq:3-management`
- Ports:
  - `5672`: AMQP protocol (giao tiếp với services)
  - `15672`: Management UI (web interface)
- Authentication: `admin` / `admin123`
- Tạo queue: `event_queue` (durable = persist qua restart)

**Vai trò của RabbitMQ:**

```
Producer → [Queue] → Consumer
  (Gửi)    (Lưu trữ)   (Nhận)
```

### Bước 3: Service 1 (Producer) hoạt động

**Chu trình hoạt động:**

```javascript
Mỗi 10 giây:
  1. Tạo event với ID, message, timestamp, priority
  2. Chuyển event thành JSON
  3. Push vào queue 'event_queue'
  4. Check queue stats (số message, số consumer)
  5. Log thông tin ra console
```

**Docker Configuration:**

- Build từ: `service1-producer/Dockerfile`
- Base image: `node:18-alpine` (nhẹ, ~40MB)
- Kết nối RabbitMQ: `amqp://admin:admin123@rabbitmq:5672`
- Restart policy: `unless-stopped` (tự khởi động lại khi lỗi)
- Network: `message_queue_network` (có thể gọi `rabbitmq` bằng hostname)

### Bước 4: Service 2 (Consumer) hoạt động

**Chu trình xử lý:**

```javascript
Liên tục lắng nghe queue:
  1. Nhận message từ queue 'event_queue'
  2. Parse JSON → event object
  3. Log thông tin event
  4. Xử lý event (giả lập 1-2 giây)
  5. Acknowledge (ACK) → RabbitMQ xóa message khỏi queue

Nếu lỗi:
  - NACK (reject) → message không requeue
```

**Docker Configuration:**

- Build từ: `service2-consumer/Dockerfile`
- Base image: `node:18-alpine`
- Prefetch: 1 (chỉ xử lý 1 message tại một thời điểm)
- Manual ACK: Chỉ xóa message sau khi xử lý thành công

## 🎯 Các khái niệm quan trọng

### 1. Message Queue (RabbitMQ)

**Là gì?**

- Hệ thống trung gian để truyền message giữa các services
- Như một "hòm thư" - Producer gửi, Consumer nhận

**Lợi ích:**
✅ **Decoupling**: Service 1 và 2 không cần biết nhau tồn tại  
✅ **Asynchronous**: Producer không chờ Consumer xử lý  
✅ **Buffering**: Queue lưu message khi Consumer quá tải  
✅ **Reliability**: Message không mất khi service restart  
✅ **Scalability**: Có thể thêm nhiều Consumer để xử lý song song

**Ví dụ thực tế:**

- 🛒 E-commerce: Order Service → Payment Service
- 📧 Email: User Action → Email Queue → Email Service
- 📊 Analytics: Event Tracking → Data Processing
- 🔔 Notification: App Event → Push Notification Service

### 2. Docker Containerization

**Là gì?**

- Đóng gói ứng dụng + dependencies vào container
- Container = isolated environment, chạy độc lập

**Trong project này:**

```yaml
docker-compose.yml:
  - Định nghĩa 3 services (rabbitmq, service1, service2)
  - Kết nối chúng qua network
  - Quản lý startup order (depends_on)
  - Auto-restart khi crash

Dockerfile (mỗi service):
  - Dùng Node.js 18 Alpine (image nhẹ)
  - Copy code + install dependencies
  - Chạy ứng dụng
```

**Lợi ích:**
✅ **Portability**: Chạy được mọi nơi có Docker  
✅ **Isolation**: Mỗi service có môi trường riêng  
✅ **Easy setup**: 1 lệnh chạy toàn bộ hệ thống  
✅ **Consistency**: Dev/Test/Prod giống nhau  
✅ **Resource efficient**: Chia sẻ OS kernel, nhẹ hơn VM

## 🔍 Chi tiết kỹ thuật

### Message Flow

```
1. Service 1 tạo event:
   {
     id: 1,
     message: "Event 1",
     timestamp: "2026-01-18T00:21:27.322Z",
     data: { status: "active", priority: 4 }
   }

2. Push vào RabbitMQ:
   channel.sendToQueue('event_queue', Buffer, { persistent: true })

3. RabbitMQ lưu vào queue:
   [event_queue] messages: 1, consumers: 1

4. Service 2 nhận từ queue:
   channel.consume('event_queue', callback, { noAck: false })

5. Xử lý và ACK:
   - Process event (1-2 seconds)
   - channel.ack(message) ✅
   - RabbitMQ xóa message khỏi queue
```

### Docker Network

```
message_queue_network (bridge):
  - rabbitmq → 172.18.0.2
  - service1-producer → 172.18.0.3
  - service2-consumer → 172.18.0.4

Services gọi nhau qua hostname:
  - service1 connect: rabbitmq:5672 (không dùng localhost)
  - service2 connect: rabbitmq:5672
```

### Health Check & Dependencies

```yaml
rabbitmq:
  healthcheck: # Kiểm tra RabbitMQ ready
    test: rabbitmq-diagnostics -q ping
    interval: 10s

service1-producer:
  depends_on:
    rabbitmq:
      condition: service_healthy # Chờ RabbitMQ healthy mới start

service2-consumer:
  depends_on:
    rabbitmq:
      condition: service_healthy
```

**Kết quả:**

- Service 1 & 2 chỉ start khi RabbitMQ đã sẵn sàng
- Tránh lỗi connection refused

## 📊 Monitoring

### RabbitMQ Management UI

- URL: http://localhost:15672
- Username: `admin` / Password: `admin123`
- Xem được:
  - Số message trong queue
  - Publish rate / Consume rate
  - Số consumers đang active
  - Memory usage, connections

### Docker Logs

```bash
# Xem logs tất cả services
docker-compose logs -f

# Logs từng service
docker-compose logs -f service1-producer
docker-compose logs -f service2-consumer
docker-compose logs -f rabbitmq

# Check container status
docker-compose ps
```

## 🎓 So sánh: Có vs Không có Message Queue

### ❌ Không có Message Queue (Direct API call)

```
Service 1 → HTTP POST → Service 2
              ↓
          Chờ response
```

**Vấn đề:**

- Service 2 down → Service 1 bị lỗi
- Service 2 chậm → Service 1 phải chờ
- Nhiều request → Service 2 quá tải
- Tight coupling: Services phụ thuộc nhau

### ✅ Có Message Queue

```
Service 1 → Push → [Queue] → Service 2
    (Done!)          ↓
                 Buffered
```

**Ưu điểm:**

- Service 2 down → Message vẫn an toàn trong queue
- Service 1 không chờ → Response nhanh
- Service 2 xử lý theo tốc độ riêng
- Loose coupling: Services độc lập
- Dễ scale: Thêm Consumer để xử lý nhanh hơn

## 💡 Tóm tắt ngắn gọn

**Message Queue (RabbitMQ):**

- 📬 Hòm thư trung gian cho các services
- 🔄 Producer gửi → Queue lưu → Consumer nhận
- ✅ Bất đồng bộ, tin cậy, có thể scale

**Docker:**

- 📦 Đóng gói ứng dụng thành containers
- 🚀 1 lệnh chạy toàn bộ hệ thống
- 🔗 Kết nối services qua network
- ♻️ Auto-restart khi lỗi

**Kết hợp MQ + Docker:**

- Hệ thống microservices hoàn chỉnh
- Dễ deploy, dễ scale, dễ maintain
- Production-ready architecture
