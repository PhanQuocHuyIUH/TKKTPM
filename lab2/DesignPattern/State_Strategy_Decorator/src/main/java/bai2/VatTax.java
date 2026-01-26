package bai2;

public class VatTax implements TaxStrategy {
    public double calculate(double price) {
        return price * 0.1;
    }
}
