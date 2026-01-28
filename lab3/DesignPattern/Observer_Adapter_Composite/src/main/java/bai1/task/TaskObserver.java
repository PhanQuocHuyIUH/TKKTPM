package bai1.task;

/**
 * Observer interface for task status notifications
 * Team members implement this interface to receive updates when task status changes
 */
public interface TaskObserver {
    /**
     * Called when a task status changes
     * @param task The task that changed
     * @param oldStatus The previous status
     * @param newStatus The new status
     */
    void onTaskUpdate(Task task, String oldStatus, String newStatus);
}
