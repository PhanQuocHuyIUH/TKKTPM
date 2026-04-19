# Observer Design Pattern Implementation

## Mô tả

Dự án này triển khai mẫu thiết kế Observer (Observer Design Pattern) cho hai trường hợp sử dụng:

1. **Hệ thống thông báo giá cổ phiếu**: Các nhà đầu tư đăng ký theo dõi cổ phiếu và nhận thông báo ngay lập tức khi giá thay đổi.
2. **Hệ thống thông báo trạng thái công việc**: Các thành viên trong nhóm nhận thông báo tự động khi trạng thái task thay đổi.

## Cấu trúc dự án

```
Observer_Pattern/
├── pom.xml
├── README.md
├── diagrams/
│   ├── STOCK_UML.md
│   └── TASK_UML.md
└── src/main/java/bai1/
    ├── stock/
    │   ├── IObserver.java          (Observer interface)
    │   ├── ISubject.java           (Subject interface)
    │   ├── Investor.java           (Concrete Observer)
    │   ├── Stock.java              (Concrete Subject)
    │   └── StockDemo.java          (Demo class)
    └── task/
        ├── TaskStatus.java         (Enum for task statuses)
        ├── ITaskObserver.java      (Observer interface)
        ├── ITaskSubject.java       (Subject interface)
        ├── TeamMember.java         (Concrete Observer)
        ├── Task.java               (Concrete Subject)
        └── TaskDemo.java           (Demo class)
```

## Observer Pattern - Giải thích

Observer Pattern là một mẫu thiết kế hành vi (behavioral design pattern) cho phép định nghĩa một cơ chế đăng ký để thông báo cho nhiều đối tượng về bất kỳ sự kiện nào xảy ra với đối tượng mà chúng đang quan sát.

### Các thành phần chính:

1. **Subject (ISubject, ITaskSubject)**: 
   - Interface định nghĩa các phương thức để quản lý observers
   - `attach()`: Đăng ký observer
   - `detach()`: Hủy đăng ký observer
   - `notifyObservers()`: Thông báo cho tất cả observers

2. **Observer (IObserver, ITaskObserver)**:
   - Interface định nghĩa phương thức `update()` để nhận thông báo

3. **Concrete Subject (Stock, Task)**:
   - Triển khai Subject interface
   - Lưu trữ trạng thái
   - Thông báo cho observers khi trạng thái thay đổi

4. **Concrete Observer (Investor, TeamMember)**:
   - Triển khai Observer interface
   - Nhận và xử lý thông báo từ Subject

## Cách chạy

### Biên dịch dự án

```bash
cd Observer_Pattern
mvn clean compile
```

### Chạy demo hệ thống cổ phiếu

```bash
mvn exec:java -Dexec.mainClass="bai1.stock.StockDemo"
```

### Chạy demo hệ thống task

```bash
mvn exec:java -Dexec.mainClass="bai1.task.TaskDemo"
```

## Kết quả mong đợi

### Stock Notification System
Khi chạy StockDemo, bạn sẽ thấy:
- Các nhà đầu tư đăng ký theo dõi cổ phiếu
- Thông báo gửi đến tất cả nhà đầu tư khi giá thay đổi
- Nhà đầu tư có thể hủy đăng ký và không nhận thông báo nữa

### Task Notification System
Khi chạy TaskDemo, bạn sẽ thấy:
- Các thành viên nhóm đăng ký theo dõi tasks
- Thông báo gửi đến tất cả thành viên khi trạng thái task thay đổi
- Thành viên có thể hủy đăng ký và không nhận thông báo nữa

## Lợi ích của Observer Pattern

1. **Loose Coupling**: Subject và Observer có thể hoạt động độc lập
2. **Mở rộng dễ dàng**: Có thể thêm observers mới mà không cần sửa Subject
3. **Dynamic Relationships**: Observers có thể đăng ký/hủy đăng ký tại runtime
4. **Broadcast Communication**: Một Subject có thể thông báo cho nhiều Observers

## Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- Maven 3.6 hoặc cao hơn
