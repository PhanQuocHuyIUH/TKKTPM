docker-compose logs -f# RabbitMQ Message Queue - Lab 1

Dự án demo về Message Queue sử dụng RabbitMQ với 2 services:

- **Service 1 (Producer)**: Push events vào RabbitMQ mỗi 10 giây
- **Service 2 (Consumer)**: Nhận và xử lý events từ RabbitMQ

## Yêu cầu

- Docker & Docker Compose
- (Optional) Node.js v18+ cho development

## Cấu trúc dự án

```
Message Queue/
├── docker-compose.yml          # Orchestration cho tất cả services
├── service1-producer/          # Service push events
│   ├── Dockerfile
│   ├── package.json
│   └── index.js
└── service2-consumer/          # Service nhận events
    ├── Dockerfile
    ├── package.json
    └── index.js
```

## Hướng dẫn chạy

### 🚀 Cách 1: Chạy tất cả bằng Docker (Khuyên dùng)

Chỉ cần 1 lệnh để chạy tất cả services:

```powershell
# Build và chạy tất cả services (RabbitMQ + Service 1 + Service 2)
docker-compose up --build -d
```

Kiểm tra logs:

```powershell
# Xem logs tất cả services
docker-compose logs -f

# Hoặc xem từng service
docker-compose logs -f service1-producer
docker-compose logs -f service2-consumer
docker-compose logs -f rabbitmq
```

Dừng tất cả:

```powershell
docker-compose down
```

RabbitMQ Management UI: http://localhost:15672

- Username: `admin`
- Password: `admin123`

---

### � Cách 2: Chạy riêng từng service để xem logs (Khuyên dùng khi debug)

Chạy từng service riêng lẻ ở foreground để xem logs trực tiếp:

**Terminal 1 - Khởi động RabbitMQ:**

```powershell
docker-compose up rabbitmq -d
```

**Terminal 2 - Chạy Service 2 (Consumer) để xem logs:**

```powershell
# Chờ RabbitMQ ready (khoảng 10 giây)
docker-compose up service2-consumer
```

**Terminal 3 - Chạy Service 1 (Producer) để xem logs:**

```powershell
docker-compose up service1-producer
```

**Lợi ích:**

- ✅ Xem logs real-time trực tiếp trên terminal
- ✅ Dễ debug và theo dõi flow
- ✅ Dừng service bằng `Ctrl+C`
- ✅ Không cần chạy `docker-compose logs -f`

**Chạy cụ thể một service:**

```powershell
# Chỉ chạy producer
docker-compose up service1-producer

# Chỉ chạy consumer
docker-compose up service2-consumer

# Chạy cả 2 services nhưng không chạy RabbitMQ
docker-compose up service1-producer service2-consumer
```

---

### 🔧 Cách 3: Chạy thủ công (Development)

**Bước 1: Khởi động RabbitMQ**

```powershell
# Chỉ chạy RabbitMQ
docker-compose up rabbitmq -d
```

**Bước 2: Cài đặt dependencies**

```powershell
cd service1-producer
npm install

cd ..\service2-consumer
npm install
```

**Bước 3: Chạy các services**

**Terminal 1 - Service 1 (Producer):**

```powershell
cd service1-producer
npm start
```

**Terminal 2 - Service 2 (Consumer):**

```powershell
cd service2-consumer
npm start
```

---

## Cách hoạt động

1. **Service 1** tự động push events vào RabbitMQ mỗi 10 giây
2. **Service 2** nhận và xử lý từng event từ queue
3. Mỗi event chứa thông tin:
   - ID
   - Message
   - Timestamp
   - Priority (1-5)

### Logs mẫu

**Service 1 (Producer):**

```
🚀 [Service 1] Producer đã khởi động và đang push events...
📊 Thông tin RabbitMQ:
   - URL: amqp://admin:admin123@rabbitmq:5672
   - Queue Name: event_queue
   - Queue Durable: true
   - Message Persistent: true
   - Interval: 10 giây

✅ [Service 1] Đã push event #1: Event 1
   📦 Queue Stats:
      - Messages in Queue: 0
      - Consumers: 1
      - Timestamp: 2026-01-18T00:21:27.322Z
```

**Service 2 (Consumer):**

```
🚀 [Service 2] Consumer đã khởi động và đang chờ events...

📨 [Service 2] Nhận được event #1
   Message: Event 1
   Timestamp: 2026-01-18T00:21:27.322Z
   Priority: 4
✅ [Service 2] Đã xử lý event #1 (1226ms)
```

## Kiểm tra trên RabbitMQ UI

1. Truy cập http://localhost:15672
2. Đăng nhập với `admin` / `admin123`
3. Vào tab **Queues** để xem queue `event_queue`
4. Có thể xem số lượng messages, rate, consumers và các thông tin khác

## Các lệnh Docker hữu ích

```powershell
# Xem trạng thái containers
docker-compose ps

# Xem logs theo thời gian thực
docker-compose logs -f

# Restart một service
docker-compose restart service1-producer

# Rebuild một service
docker-compose up --build service1-producer -d

# Dừng và xóa tất cả
docker-compose down -v
```

## Tính năng

✅ **Docker containerization** - Chạy 1 lệnh duy nhất  
✅ **Health check** - Services chờ RabbitMQ sẵn sàng  
✅ **Auto-restart** - Tự động khởi động lại khi lỗi  
✅ **Message persistence** - Durable queue & persistent messages  
✅ **Manual acknowledgment** - Đảm bảo message được xử lý  
✅ **Prefetch limit** - Kiểm soát xử lý đồng thời  
✅ **Graceful shutdown** - Đóng kết nối an toàn  
✅ **Environment variables** - Cấu hình linh hoạt  
✅ **Detailed logging** - Thông tin MQ chi tiết

## Troubleshooting

**Lỗi kết nối RabbitMQ:**

- Đảm bảo Docker đang chạy
- Kiểm tra RabbitMQ healthy: `docker-compose ps`
- Khởi động lại: `docker-compose restart rabbitmq`

**Port bị chiếm:**

- Thay đổi port trong `docker-compose.yml`
- Hoặc dừng service đang chiếm port

**Rebuild từ đầu:**

```powershell
docker-compose down -v
docker-compose up --build -d
```
