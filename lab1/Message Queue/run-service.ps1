# Script để chạy riêng các services và xem logs trực tiếp

Write-Host "=== Chọn service cần chạy ===" -ForegroundColor Cyan
Write-Host "1. Chỉ chạy RabbitMQ (background)"
Write-Host "2. Chạy Service 1 - Producer (xem logs trực tiếp)"
Write-Host "3. Chạy Service 2 - Consumer (xem logs trực tiếp)"
Write-Host "4. Chạy cả 2 services (logs trực tiếp)"
Write-Host "5. Chạy tất cả (background)"
Write-Host "6. Dừng tất cả"
Write-Host ""

$choice = Read-Host "Nhập lựa chọn (1-6)"

switch ($choice) {
    "1" {
        Write-Host "`n🐰 Khởi động RabbitMQ..." -ForegroundColor Green
        docker-compose up rabbitmq -d
        Write-Host "✅ RabbitMQ đang chạy: http://localhost:15672 (admin/admin123)" -ForegroundColor Green
    }
    "2" {
        Write-Host "`n📤 Chạy Service 1 - Producer (Ctrl+C để dừng)..." -ForegroundColor Green
        docker-compose up service1-producer
    }
    "3" {
        Write-Host "`n📥 Chạy Service 2 - Consumer (Ctrl+C để dừng)..." -ForegroundColor Green
        docker-compose up service2-consumer
    }
    "4" {
        Write-Host "`n🔄 Chạy cả 2 services (Ctrl+C để dừng)..." -ForegroundColor Green
        docker-compose up service1-producer service2-consumer
    }
    "5" {
        Write-Host "`n🚀 Chạy tất cả services..." -ForegroundColor Green
        docker-compose up --build -d
        Write-Host "✅ Tất cả services đang chạy!" -ForegroundColor Green
        Write-Host "📊 Xem logs: docker-compose logs -f" -ForegroundColor Yellow
    }
    "6" {
        Write-Host "`n⛔ Dừng tất cả services..." -ForegroundColor Red
        docker-compose down
        Write-Host "✅ Đã dừng tất cả!" -ForegroundColor Green
    }
    default {
        Write-Host "❌ Lựa chọn không hợp lệ!" -ForegroundColor Red
    }
}
