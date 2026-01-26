package bai2;

public class LuxuryTax implements TaxStrategy {
    public double calculate(double price) {
        return price * 0.2;
    }
}