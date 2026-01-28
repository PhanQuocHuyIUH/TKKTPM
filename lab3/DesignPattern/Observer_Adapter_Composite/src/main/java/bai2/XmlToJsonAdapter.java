package bai2;

import org.json.JSONObject;
import org.json.XML;

/**
 * Adapter - Chuyển đổi giữa XML và JSON
 * Cho phép XmlDataSystem làm việc với JsonDataService interface
 */
public class XmlToJsonAdapter implements JsonDataService {
    private XmlDataSystem xmlSystem;

    public XmlToJsonAdapter(XmlDataSystem xmlSystem) {
        this.xmlSystem = xmlSystem;
        System.out.println("🔌 Adapter: Đã kết nối với XmlDataSystem");
    }

    @Override
    public void processJsonData(String jsonData) {
        System.out.println("\n🔄 Adapter: Bắt đầu chuyển đổi JSON → XML");
        System.out.println("   Input JSON: " + jsonData);
        
        // Chuyển đổi JSON sang XML
        String xmlData = convertJsonToXml(jsonData);
        
        System.out.println("   Output XML: " + xmlData.substring(0, Math.min(100, xmlData.length())) + "...");
        
        // Gọi phương thức của XmlDataSystem
        xmlSystem.processXmlData(xmlData);
        System.out.println("✅ Adapter: Hoàn thành chuyển đổi và xử lý\n");
    }

    @Override
    public String getJsonData() {
        System.out.println("\n🔄 Adapter: Bắt đầu lấy dữ liệu và chuyển đổi XML → JSON");
        
        // Lấy XML data từ hệ thống
        String xmlData = xmlSystem.getXmlData();
        
        // Chuyển đổi XML sang JSON
        String jsonData = convertXmlToJson(xmlData);
        
        System.out.println("   Output JSON: " + jsonData);
        System.out.println("✅ Adapter: Hoàn thành chuyển đổi\n");
        
        return jsonData;
    }

    /**
     * Chuyển đổi từ JSON sang XML
     * Sử dụng thư viện org.json (hoặc custom implementation)
     */
    private String convertJsonToXml(String jsonData) {
        try {
            // Sử dụng thư viện org.json để chuyển đổi
            // Trong thực tế, bạn cần thêm dependency org.json vào pom.xml
            // Đây là implementation đơn giản cho demo
            
            JSONObject json = new JSONObject(jsonData);
            String xml = XML.toString(json);
            
            // Thêm root element nếu cần
            if (!xml.startsWith("<?xml")) {
                xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>" + xml + "</root>";
            }
            
            return xml;
        } catch (Exception e) {
            // Fallback: Simple manual conversion for demo
            return convertJsonToXmlSimple(jsonData);
        }
    }

    /**
     * Chuyển đổi từ XML sang JSON
     * Sử dụng thư viện org.json (hoặc custom implementation)
     */
    private String convertXmlToJson(String xmlData) {
        try {
            // Sử dụng thư viện org.json để chuyển đổi
            JSONObject json = XML.toJSONObject(xmlData);
            return json.toString(2); // Pretty print with indent
        } catch (Exception e) {
            // Fallback: Simple manual conversion for demo
            return convertXmlToJsonSimple(xmlData);
        }
    }

    /**
     * Chuyển đổi JSON sang XML đơn giản (không dùng thư viện)
     * Đây là implementation đơn giản cho mục đích demo
     */
    private String convertJsonToXmlSimple(String jsonData) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<root>\n");
        
        // Remove { } and split by comma
        String content = jsonData.trim();
        if (content.startsWith("{")) content = content.substring(1);
        if (content.endsWith("}")) content = content.substring(0, content.length() - 1);
        
        String[] pairs = content.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replace("\"", "");
                String value = keyValue[1].trim().replace("\"", "");
                xml.append("  <").append(key).append(">")
                   .append(value)
                   .append("</").append(key).append(">\n");
            }
        }
        
        xml.append("</root>");
        return xml.toString();
    }

    /**
     * Chuyển đổi XML sang JSON đơn giản (không dùng thư viện)
     * Đây là implementation đơn giản cho mục đích demo
     */
    private String convertXmlToJsonSimple(String xmlData) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        
        // Simple regex parsing (not production-ready)
        String[] lines = xmlData.split("\n");
        boolean first = true;
        
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("<") && !line.startsWith("<?") && 
                !line.startsWith("</") && !line.equals("<root>") && !line.equals("</root>")) {
                
                if (!first) json.append(",\n");
                first = false;
                
                // Extract tag name and content
                int start = line.indexOf("<") + 1;
                int end = line.indexOf(">");
                String tag = line.substring(start, end);
                
                int contentStart = end + 1;
                int contentEnd = line.lastIndexOf("</");
                String content = line.substring(contentStart, contentEnd);
                
                json.append("  \"").append(tag).append("\": \"")
                    .append(content).append("\"");
            }
        }
        
        json.append("\n}");
        return json.toString();
    }
}
