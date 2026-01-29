package bai1.task;

/**
 * Concrete Observer - TeamMember
 * Represents a team member who subscribes to task status changes
 */
public class TeamMember implements ITaskObserver {
    private String name;
    private String role;
    
    public TeamMember(String name, String role) {
        this.name = name;
        this.role = role;
    }
    
    @Override
    public void update(String taskName, TaskStatus oldStatus, TaskStatus newStatus) {
        System.out.println("📧 Thông báo đến " + role + " " + name + ": " +
                         "Task '" + taskName + "' đã chuyển từ '" + 
                         oldStatus.getDescription() + "' sang '" + 
                         newStatus.getDescription() + "'");
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
}
