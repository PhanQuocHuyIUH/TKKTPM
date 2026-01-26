package bai3;

public class FeeDecorator extends PaymentDecorator {
    public FeeDecorator(Payment payment) {
        super(payment);
    }

    public double pay(double amount) {
        return payment.pay(amount) + 20;
    }
}