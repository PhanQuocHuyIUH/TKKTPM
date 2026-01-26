package bai1;

public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        order.process();
        order.process();
        order.process();

        order.setState(new CancelledState());
        order.process();
    }
}
