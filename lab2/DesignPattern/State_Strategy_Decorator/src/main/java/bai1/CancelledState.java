package bai1;

public class CancelledState implements OrderState {
    @Override
    public void handle(OrderContext order) {
        System.out.println("Hủy đơn hàng và hoàn tiền");
    }
}