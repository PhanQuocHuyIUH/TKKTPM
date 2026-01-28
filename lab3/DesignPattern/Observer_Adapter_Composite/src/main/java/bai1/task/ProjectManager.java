package bai1.task;

/**
 * Concrete Observer - Project Manager
 * Monitors all task changes and manages project timeline
 */
public class ProjectManager implements TaskObserver {
    private String name;
    private int totalTasksMonitored;

    public ProjectManager(String name) {
        this.name = name;
        this.totalTasksMonitored = 0;
    }

    @Override
    public void onTaskUpdate(Task task, String oldStatus, String newStatus) {
        totalTasksMonitored++;
        
        System.out.println("👔 Project Manager: " + name);
        System.out.println("   📊 Theo dõi task: " + task.getTaskId() + " - " + task.getTitle());
        System.out.println("   📈 Cập nhật: " + oldStatus + " → " + newStatus);
        
        // Project management actions
        if (newStatus.equals("BLOCKED")) {
            System.out.println("   🚨 CẢNH BÁO: Task bị block! Cần can thiệp ngay!");
            System.out.println("   📞 Hành động: Liên hệ team để giải quyết vấn đề");
        } else if (newStatus.equals("DONE")) {
            System.out.println("   ✨ Hoàn thành! Cập nhật tiến độ dự án");
            System.out.println("   📋 Hành động: Review deliverable và close task");
        } else if (newStatus.equals("IN_PROGRESS")) {
            System.out.println("   ⏱️ Task đang được thực hiện, theo dõi deadline");
        } else if (newStatus.equals("TESTING")) {
            System.out.println("   🧪 Đang trong giai đoạn testing");
            System.out.println("   📋 Hành động: Đảm bảo QA resources sẵn sàng");
        }
        
        System.out.println("   📊 Tổng số task đã theo dõi: " + totalTasksMonitored);
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public int getTotalTasksMonitored() {
        return totalTasksMonitored;
    }
}
