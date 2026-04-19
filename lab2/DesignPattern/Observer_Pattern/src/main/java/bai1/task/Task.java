package bai1.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject - Task
 * Represents a task that notifies team members when status changes
 */
public class Task implements ITaskSubject {
    private String name;
    private TaskStatus status;
    private List<ITaskObserver> observers;
    
    public Task(String name) {
        this.name = name;
        this.status = TaskStatus.TODO;
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void attach(ITaskObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✓ Đã đăng ký theo dõi task: " + name);
        }
    }
    
    @Override
    public void detach(ITaskObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (observers.remove(observer)) {
            System.out.println("✗ Đã hủy đăng ký theo dõi task: " + name);
        }
    }
    
    @Override
    public void notifyObservers() {
        // This implementation notifies with current status only
        // For status changes, use the private notifyObserversWithChange method
        for (ITaskObserver observer : observers) {
            observer.update(name, status, status);
        }
    }
    
    /**
     * Change task status and notify all observers
     * @param newStatus The new status
     */
    public void setStatus(TaskStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }
        if (this.status != newStatus) {
            TaskStatus oldStatus = this.status;
            this.status = newStatus;
            System.out.println("\n🔄 Task '" + name + "' thay đổi trạng thái: " + 
                             oldStatus.getDescription() + " → " + newStatus.getDescription());
            notifyObserversWithChange(oldStatus, newStatus);
        }
    }
    
    /**
     * Notify observers with old and new status
     */
    private void notifyObserversWithChange(TaskStatus oldStatus, TaskStatus newStatus) {
        for (ITaskObserver observer : observers) {
            observer.update(name, oldStatus, newStatus);
        }
    }
    
    public String getName() {
        return name;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
}
