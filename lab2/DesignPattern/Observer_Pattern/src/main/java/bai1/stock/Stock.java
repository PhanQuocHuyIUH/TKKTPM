package bai1.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject - Stock
 * Represents a stock that notifies investors when price changes
 */
public class Stock implements ISubject {
    private String symbol;
    private double price;
    private List<IObserver> observers;
    
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void attach(IObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Đã đăng ký theo dõi cổ phiếu " + symbol);
        }
    }
    
    @Override
    public void detach(IObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("Đã hủy đăng ký theo dõi cổ phiếu " + symbol);
        }
    }
    
    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(symbol, price);
        }
    }
    
    /**
     * Set new price and notify all observers
     * @param price The new price
     */
    public void setPrice(double price) {
        System.out.println("\n--- Giá cổ phiếu " + symbol + " thay đổi từ " + 
                         this.price + " VND sang " + price + " VND ---");
        this.price = price;
        notifyObservers();
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public double getPrice() {
        return price;
    }
}
