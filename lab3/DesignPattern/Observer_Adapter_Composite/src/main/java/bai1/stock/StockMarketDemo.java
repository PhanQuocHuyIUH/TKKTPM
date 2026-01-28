package bai1.stock;

/**
 * Demo class for Stock Price Notification System using Observer Pattern
 */
public class StockMarketDemo {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║   STOCK PRICE NOTIFICATION SYSTEM - OBSERVER PATTERN     ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        // Create stocks
        Stock appleStock = new Stock("AAPL", 150.00);
        Stock googleStock = new Stock("GOOGL", 2800.00);

        // Create observers (investors and funds)
        Investor investor1 = new Investor("Nguyễn Văn A");
        Investor investor2 = new Investor("Trần Thị B");
        InvestmentFund fund1 = new InvestmentFund("Vanguard Fund", 1000000000);
        InvestmentFund fund2 = new InvestmentFund("BlackRock Fund", 2000000000);

        System.out.println("--- Đăng ký theo dõi cổ phiếu ---");
        // Register observers to Apple stock
        appleStock.attach(investor1);
        appleStock.attach(investor2);
        appleStock.attach(fund1);

        // Register observers to Google stock
        googleStock.attach(investor2);  // Investor 2 follows both stocks
        googleStock.attach(fund2);

        System.out.println("\n--- Thay đổi giá cổ phiếu AAPL ---");
        appleStock.setPrice(158.50);  // Price increase

        System.out.println("\n--- Thay đổi giá cổ phiếu GOOGL ---");
        googleStock.setPrice(2650.00);  // Price decrease

        System.out.println("\n--- Investor 1 ngừng theo dõi AAPL ---");
        appleStock.detach(investor1);

        System.out.println("\n--- Thay đổi giá cổ phiếu AAPL lần 2 ---");
        appleStock.setPrice(145.00);  // Price decrease

        System.out.println("\n--- Thêm Fund 2 theo dõi AAPL ---");
        appleStock.attach(fund2);

        System.out.println("\n--- Thay đổi giá cổ phiếu AAPL lần 3 ---");
        appleStock.setPrice(152.00);  // Price increase

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              DEMO COMPLETED SUCCESSFULLY                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}
