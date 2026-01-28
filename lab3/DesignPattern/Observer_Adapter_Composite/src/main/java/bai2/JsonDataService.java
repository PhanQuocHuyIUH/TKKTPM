package bai2;

/**
 * Target Interface - Dịch vụ JSON mà client mong đợi
 * Định nghĩa các phương thức làm việc với JSON data
 */
public interface JsonDataService {
    /**
     * Xử lý dữ liệu JSON
     * @param jsonData Dữ liệu ở định dạng JSON
     */
    void processJsonData(String jsonData);
    
    /**
     * Lấy dữ liệu ở định dạng JSON
     * @return Dữ liệu JSON
     */
    String getJsonData();
}
