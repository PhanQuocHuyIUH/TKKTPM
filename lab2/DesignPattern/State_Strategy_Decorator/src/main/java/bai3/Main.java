package bai3;

public class Main {
    public static void main(String[] args) {
        Payment payment = new BasicPayment();
        payment = new FeeDecorator(payment);
        payment = new DiscountDecorator(payment);

        System.out.println(payment.pay(1000));
    }
}
