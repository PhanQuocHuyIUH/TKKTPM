package bai1;

/**
 * Main class to run both Observer Pattern demonstrations
 */
public class ObserverPatternMain {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║         OBSERVER DESIGN PATTERN - BAI 1 DEMONSTRATIONS       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // Menu
        if (args.length == 0) {
            showMenu();
            return;
        }

        switch (args[0]) {
            case "1":
            case "stock":
                runStockDemo();
                break;
            case "2":
            case "task":
                runTaskDemo();
                break;
            case "all":
                runStockDemo();
                System.out.println("\n\n");
                runTaskDemo();
                break;
            default:
                showMenu();
        }
    }

    private static void showMenu() {
        System.out.println("Chọn demo để chạy:");
        System.out.println("  1 hoặc stock - Hệ thống thông báo giá cổ phiếu");
        System.out.println("  2 hoặc task  - Hệ thống quản lý task");
        System.out.println("  all          - Chạy cả hai demo");
        System.out.println("\nCách sử dụng:");
        System.out.println("  java bai1.ObserverPatternMain 1");
        System.out.println("  java bai1.ObserverPatternMain stock");
        System.out.println("  java bai1.ObserverPatternMain task");
        System.out.println("  java bai1.ObserverPatternMain all");
        System.out.println("\nHoặc chạy trực tiếp:");
        System.out.println("  java bai1.stock.StockMarketDemo");
        System.out.println("  java bai1.task.TaskManagementDemo");
    }

    private static void runStockDemo() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("         DEMO 1: STOCK PRICE NOTIFICATION SYSTEM");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
        bai1.stock.StockMarketDemo.main(new String[]{});
    }

    private static void runTaskDemo() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("         DEMO 2: TASK MANAGEMENT NOTIFICATION SYSTEM");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
        bai1.task.TaskManagementDemo.main(new String[]{});
    }
}
