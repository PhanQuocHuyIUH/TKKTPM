package bai1.stock;

/**
 * Observer interface for the Observer pattern
 * Observers implement this interface to receive updates from Subject
 */
public interface IObserver {
    /**
     * Update method called by Subject when state changes
     * @param symbol The stock symbol
     * @param price The new price
     */
    void update(String symbol, double price);
}
