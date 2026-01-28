package bai1.task;

/**
 * Demo class for Task Management Notification System using Observer Pattern
 */
public class TaskManagementDemo {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  TASK MANAGEMENT NOTIFICATION - OBSERVER PATTERN          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        // Create tasks
        Task task1 = new Task("TASK-001", "Implement Login Feature", 
                              "Create user authentication system", "TODO");
        Task task2 = new Task("TASK-002", "Design Dashboard UI", 
                              "Create responsive dashboard design", "TODO");
        Task task3 = new Task("TASK-003", "Fix Payment Bug", 
                              "Resolve payment gateway timeout issue", "IN_PROGRESS");

        // Create team members (observers)
        TeamMember developer1 = new TeamMember("Nguyễn Văn Anh", "Developer");
        TeamMember developer2 = new TeamMember("Trần Thị Bình", "Developer");
        TeamMember tester1 = new TeamMember("Lê Văn Cường", "Tester");
        TeamMember designer1 = new TeamMember("Phạm Thị Dung", "Designer");
        ProjectManager pm = new ProjectManager("Hoàng Minh Quân");

        System.out.println("--- Đăng ký observers cho các tasks ---\n");
        
        // Task 1: Login Feature - Developer 1, Tester, PM
        task1.addObserver(developer1);
        task1.addObserver(tester1);
        task1.addObserver(pm);

        // Task 2: Dashboard UI - Designer, Developer 2, PM
        task2.addObserver(designer1);
        task2.addObserver(developer2);
        task2.addObserver(pm);

        // Task 3: Payment Bug - Developer 2, Tester, PM
        task3.addObserver(developer2);
        task3.addObserver(tester1);
        task3.addObserver(pm);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // Simulate task status changes
        System.out.println("--- Developer 1 bắt đầu làm Task 1 ---");
        task1.setStatus("IN_PROGRESS");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Designer hoàn thành Task 2 ---");
        task2.setStatus("IN_PROGRESS");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Task 1 chuyển sang Testing ---");
        task1.setStatus("TESTING");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Task 3 gặp vấn đề và bị block ---");
        task3.setStatus("BLOCKED");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Developer 2 giải quyết được vấn đề Task 3 ---");
        task3.setStatus("IN_PROGRESS");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Task 1 hoàn thành ---");
        task1.setStatus("DONE");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Task 3 chuyển sang Testing ---");
        task3.setStatus("TESTING");

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println("--- Task 3 hoàn thành ---");
        task3.setStatus("DONE");

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              DEMO COMPLETED SUCCESSFULLY                  ║");
        System.out.println("║  Project Manager đã theo dõi: " + pm.getTotalTasksMonitored() + " task updates                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}
