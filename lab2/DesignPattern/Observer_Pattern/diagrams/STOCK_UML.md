# Stock Notification System - UML Class Diagram

## Sơ đồ lớp (Class Diagram)

```
┌─────────────────────────┐
│      <<interface>>      │
│       IObserver         │
├─────────────────────────┤
│                         │
├─────────────────────────┤
│ + update(symbol: String,│
│   price: double): void  │
└─────────────────────────┘
           △
           │
           │ implements
           │
┌─────────────────────────┐
│       Investor          │
├─────────────────────────┤
│ - name: String          │
├─────────────────────────┤
│ + Investor(name: String)│
│ + update(symbol: String,│
│   price: double): void  │
│ + getName(): String     │
└─────────────────────────┘


┌─────────────────────────┐
│      <<interface>>      │
│       ISubject          │
├─────────────────────────┤
│                         │
├─────────────────────────┤
│ + attach(observer:      │
│   IObserver): void      │
│ + detach(observer:      │
│   IObserver): void      │
│ + notifyObservers():    │
│   void                  │
└─────────────────────────┘
           △
           │
           │ implements
           │
┌─────────────────────────┐
│         Stock           │
├─────────────────────────┤
│ - symbol: String        │
│ - price: double         │
│ - observers:            │
│   List<IObserver>       │
├─────────────────────────┤
│ + Stock(symbol: String, │
│   price: double)        │
│ + attach(observer:      │
│   IObserver): void      │
│ + detach(observer:      │
│   IObserver): void      │
│ + notifyObservers():    │
│   void                  │
│ + setPrice(price:       │
│   double): void         │
│ + getSymbol(): String   │
│ + getPrice(): double    │
└─────────────────────────┘
           │
           │ observes
           │
           ▼
┌─────────────────────────┐
│       Investor          │
│    (Many instances)     │
└─────────────────────────┘
```

## Quan hệ giữa các lớp

1. **IObserver** (Interface)
   - Định nghĩa phương thức `update()` để nhận thông báo

2. **Investor** (Concrete Observer)
   - Triển khai `IObserver`
   - Nhận thông báo khi giá cổ phiếu thay đổi

3. **ISubject** (Interface)
   - Định nghĩa các phương thức quản lý observers: `attach()`, `detach()`, `notifyObservers()`

4. **Stock** (Concrete Subject)
   - Triển khai `ISubject`
   - Lưu trữ danh sách observers
   - Thông báo cho tất cả observers khi giá thay đổi (`setPrice()`)

## Luồng hoạt động (Sequence Diagram - dạng text)

```
Client         Stock         Investor1      Investor2
  |              |               |              |
  |--attach(i1)->|               |              |
  |              |---------------|              |
  |              |    đăng ký    |              |
  |              |               |              |
  |--attach(i2)->|               |              |
  |              |-------------------------------|
  |              |         đăng ký              |
  |              |               |              |
  |--setPrice()->|               |              |
  |              |--update()---->|              |
  |              |    thông báo  |              |
  |              |--update()---------------->   |
  |              |         thông báo            |
  |              |               |              |
```

## Ví dụ sử dụng

```java
// Tạo cổ phiếu
Stock vietcombank = new Stock("VCB", 85000);

// Tạo nhà đầu tư
Investor investor1 = new Investor("Nguyễn Văn A");
Investor investor2 = new Investor("Trần Thị B");

// Đăng ký theo dõi
vietcombank.attach(investor1);
vietcombank.attach(investor2);

// Thay đổi giá - tất cả investors nhận thông báo
vietcombank.setPrice(87000);

// Output:
// Thông báo đến nhà đầu tư Nguyễn Văn A: Cổ phiếu VCB có giá mới: 87000 VND
// Thông báo đến nhà đầu tư Trần Thị B: Cổ phiếu VCB có giá mới: 87000 VND
```

## Lợi ích

- **Tách biệt**: Stock và Investor không phụ thuộc chặt chẽ vào nhau
- **Mở rộng**: Dễ dàng thêm nhiều Investors mà không cần sửa Stock
- **Linh hoạt**: Investors có thể đăng ký/hủy đăng ký bất cứ lúc nào
