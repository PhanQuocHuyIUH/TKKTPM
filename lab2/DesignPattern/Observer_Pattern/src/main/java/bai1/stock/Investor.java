package bai1.stock;

/**
 * Concrete Observer - Investor
 * Represents an investor who subscribes to stock price changes
 */
public class Investor implements IObserver {
    private String name;
    
    public Investor(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String symbol, double price) {
        System.out.println("Thông báo đến nhà đầu tư " + name + ": " +
                         "Cổ phiếu " + symbol + " có giá mới: " + price + " VND");
    }
    
    public String getName() {
        return name;
    }
}
