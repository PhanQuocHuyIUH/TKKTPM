# Lab 04 - Docker Fundamentals

Tổng hợp 10 bài tập về Docker: từ cơ bản đến nâng cao.

## Danh sách bài tập

| # | Thư mục | Nội dung | Base Image | Cổng |
|---|---------|----------|------------|------|
| 1 | [bai1-nodejs](./bai1-nodejs/) | Node.js HTTP Server | `node:18` | 3000 |
| 2 | [bai2-flask](./bai2-flask/) | Python Flask App | `python:3.9` | 5000 |
| 3 | [bai3-react](./bai3-react/) | React App (multi-stage) | `node:18-alpine` → `nginx:alpine` | 80 |
| 4 | [bai4-nginx](./bai4-nginx/) | Static HTML với Nginx | `nginx:latest` | 80 |
| 5 | [bai5-go](./bai5-go/) | Go HTTP Server | `golang:1.21-alpine` → `alpine` | 8080 |
| 6 | [bai6-multistage](./bai6-multistage/) | Multi-stage Build (TypeScript) | `node:18` → `node:18-alpine` | 3000 |
| 7 | [bai7-envvar](./bai7-envvar/) | Biến môi trường ENV | `python:3.9` | - |
| 8 | [bai8-postgres](./bai8-postgres/) | PostgreSQL + Auto Init SQL | `postgres:15` | 5432 |
| 9 | [bai9-redis](./bai9-redis/) | Redis + Custom Config | `redis:latest` | 6379 |
| 10 | [bai10-php](./bai10-php/) | PHP + Apache | `php:8.2-apache` | 80 |

---

## Cấu trúc thư mục
```
Lab04/
├── README.md
├── bai1-nodejs/
│   ├── Dockerfile
│   ├── app.js
│   ├── package.json
│   └── README.md
├── bai2-flask/
│   ├── Dockerfile
│   ├── app.py
│   ├── requirements.txt
│   └── README.md
├── bai3-react/
│   ├── Dockerfile
│   ├── package.json
│   ├── public/index.html
│   ├── src/App.js
│   ├── src/index.js
│   └── README.md
├── bai4-nginx/
│   ├── Dockerfile
│   ├── index.html
│   └── README.md
├── bai5-go/
│   ├── Dockerfile
│   ├── go.mod
│   ├── main.go
│   └── README.md
├── bai6-multistage/
│   ├── Dockerfile
│   ├── package.json
│   ├── tsconfig.json
│   ├── src/server.ts
│   └── README.md
├── bai7-envvar/
│   ├── Dockerfile
│   ├── app.py
│   └── README.md
├── bai8-postgres/
│   ├── Dockerfile
│   ├── init.sql
│   └── README.md
├── bai9-redis/
│   ├── Dockerfile
│   ├── redis.conf
│   └── README.md
└── bai10-php/
    ├── Dockerfile
    ├── index.php
    └── README.md
```

---

## Yêu cầu hệ thống
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) đã được cài đặt và đang chạy
- Kiểm tra: `docker --version`

## Quy trình chung cho mỗi bài

```bash
# 1. Di chuyển vào thư mục bài
cd baiX-tenBai

# 2. Build image
docker build -t ten-image .

# 3. Chạy container
docker run -d -p <host-port>:<container-port> --name ten-container ten-image

# 4. Kiểm tra
curl http://localhost:<host-port>
# hoặc mở trình duyệt

# 5. Xem logs
docker logs ten-container

# 6. Dọn dẹp
docker stop ten-container
docker rm ten-container
docker rmi ten-image
```

## Các lệnh Docker hữu ích

```bash
# Xem danh sách container đang chạy
docker ps

# Xem tất cả container (kể cả đã dừng)
docker ps -a

# Xem danh sách image
docker images

# Vào shell bên trong container
docker exec -it <container-name> sh

# Xem thông tin chi tiết container
docker inspect <container-name>

# Xóa tất cả container đã dừng
docker container prune

# Xóa tất cả image không dùng
docker image prune
```

---

## Khái niệm chính

| Khái niệm | Mô tả |
|-----------|-------|
| **Image** | Template read-only chứa OS + runtime + app |
| **Container** | Instance đang chạy của một image |
| **Dockerfile** | Script định nghĩa cách build image |
| **Layer** | Mỗi lệnh trong Dockerfile tạo một layer, được cache |
| **Volume** | Lưu trữ dữ liệu bền vững ngoài container |
| **Port mapping** | `-p host:container` — map cổng host sang container |
| **ENV** | Biến môi trường khai báo trong Dockerfile |
| **Multi-stage** | Dùng nhiều `FROM` để tách bước build và run |
