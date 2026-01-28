package bai1.stock;

/**
 * Observer interface for stock price notifications
 * Observers implement this interface to receive updates when stock prices change
 */
public interface Observer {
    /**
     * Called when the stock price changes
     * @param stock The stock that changed
     * @param oldPrice The previous price
     * @param newPrice The new price
     */
    void update(Stock stock, double oldPrice, double newPrice);
}
