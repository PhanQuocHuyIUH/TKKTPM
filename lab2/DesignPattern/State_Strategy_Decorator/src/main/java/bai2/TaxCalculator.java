package bai2;

public class TaxCalculator {
    private TaxStrategy strategy;

    public TaxCalculator(TaxStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double price) {
        return strategy.calculate(price);
    }
}