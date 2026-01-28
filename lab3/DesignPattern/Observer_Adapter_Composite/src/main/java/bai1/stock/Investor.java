package bai1.stock;

/**
 * Concrete Observer - Individual Investor
 * Receives notifications when stock prices change
 */
public class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        String trend = newPrice > oldPrice ? "📈 TĂNG" : "📉 GIẢM";
        
        System.out.println("👤 Investor [" + name + "] nhận thông báo:");
        System.out.println("   Cổ phiếu: " + stock.getSymbol());
        System.out.println("   Giá cũ: $" + String.format("%.2f", oldPrice));
        System.out.println("   Giá mới: $" + String.format("%.2f", newPrice));
        System.out.println("   Biến động: " + trend + " " + String.format("%.2f", Math.abs(changePercent)) + "%");
        
        // Investment decision logic
        if (changePercent > 5) {
            System.out.println("   ⚠️ Quyết định: Cân nhắc BÁN để chốt lời!");
        } else if (changePercent < -5) {
            System.out.println("   ⚠️ Quyết định: Cân nhắc MUA thêm khi giá giảm!");
        } else {
            System.out.println("   ℹ️ Quyết định: GIỮ cổ phiếu, theo dõi tiếp.");
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }
}
