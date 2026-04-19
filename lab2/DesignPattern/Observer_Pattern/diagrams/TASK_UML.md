# Task Notification System - UML Class Diagram

## Sơ đồ lớp (Class Diagram)

```
┌─────────────────────────┐
│       <<enum>>          │
│      TaskStatus         │
├─────────────────────────┤
│ TODO                    │
│ IN_PROGRESS             │
│ REVIEW                  │
│ COMPLETED               │
├─────────────────────────┤
│ - description: String   │
├─────────────────────────┤
│ + getDescription():     │
│   String                │
└─────────────────────────┘


┌─────────────────────────┐
│      <<interface>>      │
│    ITaskObserver        │
├─────────────────────────┤
│                         │
├─────────────────────────┤
│ + update(taskName:      │
│   String, oldStatus:    │
│   TaskStatus, newStatus:│
│   TaskStatus): void     │
└─────────────────────────┘
           △
           │
           │ implements
           │
┌─────────────────────────┐
│      TeamMember         │
├─────────────────────────┤
│ - name: String          │
│ - role: String          │
├─────────────────────────┤
│ + TeamMember(name:      │
│   String, role: String) │
│ + update(taskName:      │
│   String, oldStatus:    │
│   TaskStatus, newStatus:│
│   TaskStatus): void     │
│ + getName(): String     │
│ + getRole(): String     │
└─────────────────────────┘


┌─────────────────────────┐
│      <<interface>>      │
│     ITaskSubject        │
├─────────────────────────┤
│                         │
├─────────────────────────┤
│ + attach(observer:      │
│   ITaskObserver): void  │
│ + detach(observer:      │
│   ITaskObserver): void  │
│ + notifyObservers():    │
│   void                  │
└─────────────────────────┘
           △
           │
           │ implements
           │
┌─────────────────────────┐
│          Task           │
├─────────────────────────┤
│ - name: String          │
│ - status: TaskStatus    │
│ - observers:            │
│   List<ITaskObserver>   │
├─────────────────────────┤
│ + Task(name: String)    │
│ + attach(observer:      │
│   ITaskObserver): void  │
│ + detach(observer:      │
│   ITaskObserver): void  │
│ + notifyObservers():    │
│   void                  │
│ + setStatus(newStatus:  │
│   TaskStatus): void     │
│ + getName(): String     │
│ + getStatus():          │
│   TaskStatus            │
└─────────────────────────┘
           │
           │ uses
           │
           ▼
┌─────────────────────────┐
│      TaskStatus         │
└─────────────────────────┘

           │
           │ observes
           │
           ▼
┌─────────────────────────┐
│      TeamMember         │
│    (Many instances)     │
└─────────────────────────┘
```

## Quan hệ giữa các lớp

1. **TaskStatus** (Enum)
   - Định nghĩa các trạng thái của task: TODO, IN_PROGRESS, REVIEW, COMPLETED

2. **ITaskObserver** (Interface)
   - Định nghĩa phương thức `update()` để nhận thông báo về thay đổi trạng thái

3. **TeamMember** (Concrete Observer)
   - Triển khai `ITaskObserver`
   - Nhận thông báo khi trạng thái task thay đổi

4. **ITaskSubject** (Interface)
   - Định nghĩa các phương thức quản lý observers: `attach()`, `detach()`, `notifyObservers()`

5. **Task** (Concrete Subject)
   - Triển khai `ITaskSubject`
   - Lưu trữ danh sách observers
   - Thông báo cho tất cả observers khi trạng thái thay đổi (`setStatus()`)

## Luồng hoạt động (Sequence Diagram - dạng text)

```
Client       Task         TeamMember1    TeamMember2
  |            |               |              |
  |--attach(m1)->              |              |
  |            |---------------|              |
  |            |    đăng ký    |              |
  |            |               |              |
  |--attach(m2)->              |              |
  |            |-------------------------------|
  |            |         đăng ký              |
  |            |               |              |
  |--setStatus(IN_PROGRESS)--> |              |
  |            |--update()---->|              |
  |            |    thông báo  |              |
  |            |--update()---------------->   |
  |            |         thông báo            |
  |            |               |              |
  |--setStatus(COMPLETED)----> |              |
  |            |--update()---->|              |
  |            |--update()---------------->   |
  |            |               |              |
```

## Ví dụ sử dụng

```java
// Tạo task
Task task = new Task("Phát triển tính năng đăng nhập");

// Tạo thành viên nhóm
TeamMember pm = new TeamMember("Nguyễn Văn A", "Project Manager");
TeamMember dev = new TeamMember("Trần Thị B", "Developer");

// Đăng ký theo dõi
task.attach(pm);
task.attach(dev);

// Thay đổi trạng thái - tất cả team members nhận thông báo
task.setStatus(TaskStatus.IN_PROGRESS);

// Output:
// 📧 Thông báo đến Project Manager Nguyễn Văn A: Task 'Phát triển tính năng đăng nhập' 
//    đã chuyển từ 'Chưa bắt đầu' sang 'Đang thực hiện'
// 📧 Thông báo đến Developer Trần Thị B: Task 'Phát triển tính năng đăng nhập' 
//    đã chuyển từ 'Chưa bắt đầu' sang 'Đang thực hiện'
```

## Lợi ích

- **Theo dõi tiến độ**: Tất cả thành viên nhóm được cập nhật tự động
- **Tách biệt**: Task và TeamMember không phụ thuộc chặt chẽ vào nhau
- **Mở rộng**: Dễ dàng thêm nhiều TeamMembers hoặc TaskStatus mới
- **Linh hoạt**: TeamMembers có thể đăng ký/hủy đăng ký bất cứ lúc nào
- **Rõ ràng**: Enum TaskStatus giúp quản lý trạng thái rõ ràng và an toàn
