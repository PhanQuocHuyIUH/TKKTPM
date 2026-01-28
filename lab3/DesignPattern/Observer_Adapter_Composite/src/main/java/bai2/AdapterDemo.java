package bai2;

/**
 * Demo class cho Adapter Pattern
 * Minh họa cách sử dụng Adapter để chuyển đổi giữa JSON và XML
 */
public class AdapterDemo {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║        ADAPTER PATTERN - XML to JSON Conversion               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // Kịch bản 1: Sử dụng JsonWebService trực tiếp
        demonstrateDirectJsonService();

        System.out.println("\n" + "=".repeat(65) + "\n");

        // Kịch bản 2: Sử dụng Adapter để làm việc với XmlDataSystem
        demonstrateAdapterUsage();

        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║              DEMO COMPLETED SUCCESSFULLY                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    /**
     * Demo sử dụng JsonWebService trực tiếp
     */
    private static void demonstrateDirectJsonService() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│  Scenario 1: Sử dụng JsonWebService trực tiếp              │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        JsonDataService jsonService = new JsonWebService();

        // Tạo dữ liệu JSON
        String jsonData = createSampleJsonData("user", "Alice", "alice@example.com", "30");

        System.out.println("📤 Client gửi JSON data:");
        System.out.println(jsonData + "\n");

        // Xử lý JSON data
        jsonService.processJsonData(jsonData);

        // Lấy lại JSON data
        System.out.println("\n📥 Client yêu cầu lấy dữ liệu:");
        String retrievedData = jsonService.getJsonData();
        System.out.println("✅ Nhận được: " + retrievedData);
    }

    /**
     * Demo sử dụng Adapter với XmlDataSystem
     */
    private static void demonstrateAdapterUsage() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│  Scenario 2: Sử dụng Adapter với XmlDataSystem             │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        // Tạo hệ thống XML (legacy system)
        XmlDataSystem xmlSystem = new XmlDataSystem();

        // Tạo Adapter
        JsonDataService adaptedService = new XmlToJsonAdapter(xmlSystem);

        // Tạo dữ liệu JSON từ client
        String jsonData1 = createSampleJsonData("product", "Laptop", "Electronics", "1200");

        System.out.println("📤 Client gửi JSON data:");
        System.out.println(jsonData1);

        // Client gửi JSON, nhưng hệ thống nhận XML nhờ Adapter
        adaptedService.processJsonData(jsonData1);

        // Client yêu cầu JSON, nhưng lấy từ XML system nhờ Adapter
        System.out.println("📥 Client yêu cầu lấy dữ liệu JSON:");
        String retrievedJson = adaptedService.getJsonData();
        System.out.println("✅ Client nhận được JSON: " + retrievedJson);

        System.out.println("\n" + "-".repeat(65) + "\n");

        // Demo với dữ liệu khác
        String jsonData2 = createSampleJsonData("order", "ORD-12345", "Shipped", "150.75");
        
        System.out.println("📤 Client gửi JSON data mới:");
        System.out.println(jsonData2);

        adaptedService.processJsonData(jsonData2);

        System.out.println("📥 Client yêu cầu lấy dữ liệu JSON:");
        String retrievedJson2 = adaptedService.getJsonData();
        System.out.println("✅ Client nhận được JSON: " + retrievedJson2);
    }

    /**
     * Tạo JSON data mẫu
     */
    private static String createSampleJsonData(String type, String field1, String field2, String field3) {
        switch (type) {
            case "user":
                return String.format(
                    "{\n  \"name\": \"%s\",\n  \"email\": \"%s\",\n  \"age\": \"%s\"\n}",
                    field1, field2, field3
                );
            case "product":
                return String.format(
                    "{\n  \"name\": \"%s\",\n  \"category\": \"%s\",\n  \"price\": \"%s\"\n}",
                    field1, field2, field3
                );
            case "order":
                return String.format(
                    "{\n  \"orderId\": \"%s\",\n  \"status\": \"%s\",\n  \"total\": \"%s\"\n}",
                    field1, field2, field3
                );
            default:
                return "{}";
        }
    }
}
