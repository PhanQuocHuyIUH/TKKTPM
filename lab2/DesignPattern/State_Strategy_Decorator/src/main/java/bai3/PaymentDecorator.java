package bai3;

public abstract class PaymentDecorator implements Payment {
    protected Payment payment;

    public PaymentDecorator(Payment payment) {
        this.payment = payment;
    }
}