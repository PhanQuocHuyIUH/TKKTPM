package bai1.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class representing a task in project management
 * Maintains a list of observers and notifies them when status changes
 */
public class Task {
    private String taskId;
    private String title;
    private String description;
    private String status;  // TODO, IN_PROGRESS, TESTING, DONE, BLOCKED
    private List<TaskObserver> observers;

    public Task(String taskId, String title, String description, String initialStatus) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = initialStatus;
        this.observers = new ArrayList<>();
    }

    /**
     * Add an observer to this task
     * @param observer The observer to add
     */
    public void addObserver(TaskObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer added to task " + taskId);
        }
    }

    /**
     * Remove an observer from this task
     * @param observer The observer to remove
     */
    public void removeObserver(TaskObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer removed from task " + taskId);
        }
    }

    /**
     * Notify all observers about status change
     * @param oldStatus The previous status
     * @param newStatus The new status
     */
    private void notifyObservers(String oldStatus, String newStatus) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  Task Status Changed - Notifying Team Members             ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("📋 Task: " + taskId + " - " + title);
        System.out.println("📊 Status: " + oldStatus + " → " + newStatus + "\n");
        
        for (TaskObserver observer : observers) {
            observer.onTaskUpdate(this, oldStatus, newStatus);
        }
    }

    /**
     * Set new status and notify observers
     * @param newStatus The new status
     */
    public void setStatus(String newStatus) {
        if (!this.status.equals(newStatus)) {
            String oldStatus = this.status;
            this.status = newStatus;
            notifyObservers(oldStatus, newStatus);
        }
    }

    // Getters
    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
