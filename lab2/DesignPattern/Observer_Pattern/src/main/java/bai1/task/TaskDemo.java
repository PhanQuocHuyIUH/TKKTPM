package bai1.task;

/**
 * Demo class to demonstrate the Observer pattern with Task notification system
 */
public class TaskDemo {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG QUẢN LÝ VÀ THÔNG BÁO TASK ===\n");
        
        // Create tasks
        Task task1 = new Task("Phát triển tính năng đăng nhập");
        Task task2 = new Task("Viết unit test cho API");
        Task task3 = new Task("Thiết kế giao diện dashboard");
        
        // Create team members
        TeamMember pm = new TeamMember("Nguyễn Văn A", "Project Manager");
        TeamMember dev1 = new TeamMember("Trần Thị B", "Developer");
        TeamMember dev2 = new TeamMember("Lê Văn C", "Developer");
        TeamMember tester = new TeamMember("Phạm Thị D", "Tester");
        
        // Team members subscribe to tasks
        System.out.println("--- Đăng ký theo dõi task ---");
        task1.attach(pm);
        task1.attach(dev1);
        task1.attach(tester);
        
        task2.attach(pm);
        task2.attach(dev2);
        
        task3.attach(pm);
        task3.attach(dev1);
        
        // Task status changes - observers get notified
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task1.setStatus(TaskStatus.REVIEW);
        
        task2.setStatus(TaskStatus.IN_PROGRESS);
        
        task3.setStatus(TaskStatus.IN_PROGRESS);
        task3.setStatus(TaskStatus.COMPLETED);
        
        // Team member unsubscribes
        System.out.println("\n--- Hủy đăng ký ---");
        task1.detach(tester);
        
        // Another status change
        task1.setStatus(TaskStatus.COMPLETED);
        task2.setStatus(TaskStatus.COMPLETED);
    }
}
