package bai1.task;

/**
 * Concrete Observer - Team Member
 * Receives notifications when task status changes
 */
public class TeamMember implements TaskObserver {
    private String name;
    private String role;

    public TeamMember(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void onTaskUpdate(Task task, String oldStatus, String newStatus) {
        System.out.println("👨‍💻 Team Member: " + name + " (" + role + ")");
        System.out.println("   📬 Nhận thông báo cập nhật task: " + task.getTaskId());
        System.out.println("   📝 Tiêu đề: " + task.getTitle());
        System.out.println("   🔄 Trạng thái: " + oldStatus + " → " + newStatus);
        
        // Role-specific actions
        switch (role.toUpperCase()) {
            case "DEVELOPER":
                if (newStatus.equals("TODO")) {
                    System.out.println("   ✅ Hành động: Sẵn sàng bắt đầu coding");
                } else if (newStatus.equals("TESTING")) {
                    System.out.println("   ✅ Hành động: Chuẩn bị hỗ trợ QA nếu có bug");
                } else if (newStatus.equals("DONE")) {
                    System.out.println("   🎉 Hành động: Chuyển sang task tiếp theo");
                }
                break;
            case "TESTER":
                if (newStatus.equals("TESTING")) {
                    System.out.println("   ✅ Hành động: Bắt đầu test case và báo cáo bug");
                } else if (newStatus.equals("DONE")) {
                    System.out.println("   ✅ Hành động: Xác nhận test passed");
                }
                break;
            case "DESIGNER":
                if (newStatus.equals("TODO")) {
                    System.out.println("   ✅ Hành động: Kiểm tra design spec đã đầy đủ");
                }
                break;
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
