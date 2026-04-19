package bai1.stock;

/**
 * Demo class to demonstrate the Observer pattern with Stock notification system
 */
public class StockDemo {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG THÔNG BÁO GIÁ CỔ PHIẾU ===\n");
        
        // Create stocks
        Stock vietcombank = new Stock("VCB", 85000);
        Stock vingroup = new Stock("VIC", 45000);
        
        // Create investors
        Investor investor1 = new Investor("Nguyễn Văn A");
        Investor investor2 = new Investor("Trần Thị B");
        Investor investor3 = new Investor("Lê Văn C");
        
        // Investors subscribe to stocks
        System.out.println("--- Đăng ký theo dõi ---");
        vietcombank.attach(investor1);
        vietcombank.attach(investor2);
        vingroup.attach(investor2);
        vingroup.attach(investor3);
        
        // Price changes - observers get notified
        vietcombank.setPrice(87000);
        vingroup.setPrice(46500);
        
        // Investor unsubscribes
        System.out.println("\n--- Hủy đăng ký ---");
        vietcombank.detach(investor1);
        
        // Another price change
        vietcombank.setPrice(88500);
        vingroup.setPrice(44000);
    }
}
