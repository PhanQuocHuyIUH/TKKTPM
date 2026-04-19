package bai1.task;

/**
 * Enum representing the status of a task
 */
public enum TaskStatus {
    TODO("Chưa bắt đầu"),
    IN_PROGRESS("Đang thực hiện"),
    REVIEW("Đang review"),
    COMPLETED("Hoàn thành");
    
    private final String description;
    
    TaskStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
