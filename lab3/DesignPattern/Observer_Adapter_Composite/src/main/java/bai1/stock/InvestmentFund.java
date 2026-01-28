package bai1.stock;

/**
 * Concrete Observer - Investment Fund
 * Represents an institutional investor tracking stock prices
 */
public class InvestmentFund implements Observer {
    private String fundName;
    private double totalPortfolioValue;

    public InvestmentFund(String fundName, double totalPortfolioValue) {
        this.fundName = fundName;
        this.totalPortfolioValue = totalPortfolioValue;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        String trend = newPrice > oldPrice ? "TĂNG" : "GIẢM";
        
        System.out.println("🏢 Investment Fund [" + fundName + "] nhận thông báo:");
        System.out.println("   Cổ phiếu: " + stock.getSymbol());
        System.out.println("   Biến động giá: " + trend + " " + String.format("%.2f", Math.abs(changePercent)) + "%");
        System.out.println("   Giá hiện tại: $" + String.format("%.2f", newPrice));
        
        // Fund-specific analysis
        if (Math.abs(changePercent) > 3) {
            System.out.println("   📊 Hành động: Phân tích lại danh mục đầu tư");
            System.out.println("   📝 Báo cáo cho ban quản lý quỹ");
        } else {
            System.out.println("   ✓ Trong ngưỡng chấp nhận được");
        }
        System.out.println();
    }

    public String getFundName() {
        return fundName;
    }
}
