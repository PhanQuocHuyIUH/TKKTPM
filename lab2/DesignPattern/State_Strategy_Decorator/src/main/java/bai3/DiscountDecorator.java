package bai3;

public class DiscountDecorator extends PaymentDecorator {
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    public double pay(double amount) {
        return payment.pay(amount) - 50;
    }
}