package bai1.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class representing a stock
 * Maintains a list of observers and notifies them when price changes
 */
public class Stock {
    private String symbol;
    private double price;
    private List<Observer> observers;

    public Stock(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.price = initialPrice;
        this.observers = new ArrayList<>();
    }

    /**
     * Attach an observer to this stock
     * @param observer The observer to add
     */
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer attached to stock " + symbol);
        }
    }

    /**
     * Detach an observer from this stock
     * @param observer The observer to remove
     */
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer detached from stock " + symbol);
        }
    }

    /**
     * Notify all observers about price change
     * @param oldPrice The previous price
     * @param newPrice The new price
     */
    private void notifyObservers(double oldPrice, double newPrice) {
        System.out.println("\n=== Notifying observers for " + symbol + " ===");
        for (Observer observer : observers) {
            observer.update(this, oldPrice, newPrice);
        }
    }

    /**
     * Set new price and notify observers
     * @param newPrice The new price
     */
    public void setPrice(double newPrice) {
        if (this.price != newPrice) {
            double oldPrice = this.price;
            this.price = newPrice;
            notifyObservers(oldPrice, newPrice);
        }
    }

    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
