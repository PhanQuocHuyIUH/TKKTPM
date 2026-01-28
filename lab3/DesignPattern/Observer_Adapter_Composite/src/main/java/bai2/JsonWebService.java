package bai2;

/**
 * Concrete implementation - Dịch vụ web thuần JSON
 * Triển khai trực tiếp JsonDataService interface
 */
public class JsonWebService implements JsonDataService {
    private String jsonData;

    public JsonWebService() {
        this.jsonData = "";
    }

    @Override
    public void processJsonData(String jsonData) {
        this.jsonData = jsonData;
        System.out.println("🌐 JsonWebService: Đã nhận và xử lý JSON data");
        System.out.println("   " + jsonData);
    }

    @Override
    public String getJsonData() {
        System.out.println("🌐 JsonWebService: Trả về JSON data");
        return jsonData;
    }
}
