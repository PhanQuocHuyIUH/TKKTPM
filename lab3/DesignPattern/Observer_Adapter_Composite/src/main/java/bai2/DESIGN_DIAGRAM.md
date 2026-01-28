# Adapter Design Pattern - UML Diagram

## Bài toán: Chuyển đổi giữa XML và JSON

Một dịch vụ web yêu cầu đầu vào ở định dạng JSON, nhưng một hệ thống khác chỉ hỗ trợ XML.
Cần viết một adapter để chuyển đổi dữ liệu từ XML sang JSON và ngược lại.

## Sơ đồ UML - Adapter Pattern
Z
```
┌─────────────────────────────────────────────────────────────────┐
│                    <<interface>>                                 │
│                    JsonDataService                               │
├─────────────────────────────────────────────────────────────────┤
│ + processJsonData(jsonData: String): void                        │
│ + getJsonData(): String                                          │
└─────────────────────────────────────────────────────────────────┘
                          △
                          │
                          │ implements
                          │
         ┌────────────────┴────────────────────┐
         │                                      │
┌────────┴──────────────┐         ┌────────────┴──────────────────┐
│  JsonWebService       │         │  XmlToJsonAdapter             │
├───────────────────────┤         ├───────────────────────────────┤
│ - data: String        │         │ - xmlSystem: XmlDataSystem    │
├───────────────────────┤         ├───────────────────────────────┤
│ + processJsonData()   │         │ + processJsonData()           │
│ + getJsonData()       │         │ + getJsonData()               │
└───────────────────────┘         │ - convertXmlToJson()          │
                                  │ - convertJsonToXml()          │
                                  └────────────────┬──────────────┘
                                                   │
                                                   │ uses
                                                   │
                                                   ▼
                         ┌─────────────────────────────────────────┐
                         │        XmlDataSystem                     │
                         │         (Adaptee)                        │
                         ├─────────────────────────────────────────┤
                         │ - xmlData: String                        │
                         ├─────────────────────────────────────────┤
                         │ + processXmlData(xmlData: String): void  │
                         │ + getXmlData(): String                   │
                         └─────────────────────────────────────────┘
```

## Sơ đồ tuần tự (Sequence Diagram)

```
Client          XmlToJsonAdapter       XmlDataSystem
  │                    │                      │
  │ processJsonData()  │                      │
  │───────────────────>│                      │
  │                    │ convertJsonToXml()   │
  │                    │──┐                   │
  │                    │  │                   │
  │                    │<─┘                   │
  │                    │ processXmlData()     │
  │                    │─────────────────────>│
  │                    │                      │ (stores XML data)
  │                    │                      │──┐
  │                    │                      │  │
  │                    │                      │<─┘
  │                    │<─────────────────────│
  │<───────────────────│                      │
  │                    │                      │
  │   getJsonData()    │                      │
  │───────────────────>│                      │
  │                    │   getXmlData()       │
  │                    │─────────────────────>│
  │                    │<─────────────────────│
  │                    │ convertXmlToJson()   │
  │                    │──┐                   │
  │                    │  │                   │
  │                    │<─┘                   │
  │<───────────────────│                      │
  │                    │                      │
```

## Các thành phần chính

### 1. Target Interface (JsonDataService)

- Interface mà client mong đợi
- Định nghĩa các phương thức làm việc với JSON

### 2. Adaptee (XmlDataSystem)

- Hệ thống hiện có chỉ hỗ trợ XML
- Không tương thích trực tiếp với Target Interface

### 3. Adapter (XmlToJsonAdapter)

- Triển khai Target Interface
- Chứa reference đến Adaptee
- Chuyển đổi giữa JSON và XML
- Gọi các phương thức của Adaptee

### 4. Client

- Sử dụng Target Interface
- Không biết về sự tồn tại của Adaptee

## Quy trình hoạt động

1. **Client gọi processJsonData(jsonData)**
   - Adapter nhận JSON data
   - Chuyển đổi JSON → XML
   - Gọi xmlSystem.processXmlData(xmlData)

2. **Client gọi getJsonData()**
   - Adapter gọi xmlSystem.getXmlData()
   - Nhận XML data
   - Chuyển đổi XML → JSON
   - Trả về JSON data cho client

## Ưu điểm của Adapter Pattern

1. **Single Responsibility Principle**: Tách biệt logic chuyển đổi
2. **Open/Closed Principle**: Thêm adapter mới không sửa code cũ
3. **Reusability**: Tái sử dụng code hiện có
4. **Flexibility**: Dễ dàng thay đổi cách chuyển đổi

## Use Cases

- Tích hợp hệ thống legacy
- Chuyển đổi giữa các định dạng dữ liệu
- Làm việc với third-party libraries
- Chuẩn hóa interface cho nhiều implementations khác nhau
