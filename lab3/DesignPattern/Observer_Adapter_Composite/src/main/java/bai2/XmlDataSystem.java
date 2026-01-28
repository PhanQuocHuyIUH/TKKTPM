package bai2;

/**
 * Adaptee - Hệ thống hiện có chỉ hỗ trợ XML
 * Đây là hệ thống legacy không thể thay đổi
 */
public class XmlDataSystem {
    private String xmlData;

    public XmlDataSystem() {
        this.xmlData = "";
    }

    /**
     * Xử lý dữ liệu XML
     * @param xmlData Dữ liệu ở định dạng XML
     */
    public void processXmlData(String xmlData) {
        this.xmlData = xmlData;
        System.out.println("📦 XmlDataSystem: Đã nhận và lưu trữ XML data");
        System.out.println("   " + xmlData.substring(0, Math.min(100, xmlData.length())) + "...");
    }

    /**
     * Lấy dữ liệu XML đã lưu
     * @return Dữ liệu XML
     */
    public String getXmlData() {
        System.out.println("📦 XmlDataSystem: Trả về XML data");
        return xmlData;
    }

    /**
     * Mô phỏng xử lý nội bộ của hệ thống XML
     */
    public void processInternalXml() {
        System.out.println("⚙️ XmlDataSystem: Đang xử lý XML data nội bộ...");
    }
}
