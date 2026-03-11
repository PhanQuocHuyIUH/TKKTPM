# Bài 1: Node.js Docker Application

## Mô tả
Ứng dụng Node.js đơn giản hiển thị `Hello, Docker!` chạy trên cổng 3000, không cần framework ngoài.

## Cấu trúc file
```
bai1-nodejs/
├── Dockerfile
├── app.js
├── package.json
└── README.md
```

## Giải thích Dockerfile
| Lệnh | Ý nghĩa |
|------|---------|
| `FROM node:18` | Sử dụng image Node.js 18 làm base |
| `WORKDIR /app` | Thiết lập thư mục làm việc trong container |
| `COPY package*.json ./` | Copy file package.json trước để tận dụng Docker layer cache |
| `RUN npm install` | Cài đặt các dependencies |
| `COPY . .` | Copy toàn bộ source code vào container |
| `EXPOSE 3000` | Khai báo cổng ứng dụng lắng nghe |
| `CMD ["node", "app.js"]` | Lệnh mặc định để chạy ứng dụng |

## Hướng dẫn chạy

### Bước 1: Build Docker Image
```bash
docker build -t hello-nodejs .
```

### Bước 2: Chạy Container
```bash
docker run -d -p 3000:3000 --name hello-nodejs-container hello-nodejs
```
- `-d` : chạy ở chế độ nền (detached)
- `-p 3000:3000` : map cổng 3000 host → 3000 container
- `--name` : đặt tên cho container

### Bước 3: Kiểm tra
Mở trình duyệt tại **http://localhost:3000** hoặc dùng curl:
```bash
curl http://localhost:3000
```
**Kết quả mong đợi:** `Hello, Docker!`

### Xem logs
```bash
docker logs hello-nodejs-container
```

### Dừng và xóa container
```bash
docker stop hello-nodejs-container
docker rm hello-nodejs-container
```

### Xóa image
```bash
docker rmi hello-nodejs
```
