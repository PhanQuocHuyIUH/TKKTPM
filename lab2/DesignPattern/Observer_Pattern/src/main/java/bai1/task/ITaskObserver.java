package bai1.task;

/**
 * Task Observer interface
 * Observers implement this interface to receive task status updates
 */
public interface ITaskObserver {
    /**
     * Update method called when task status changes
     * @param taskName The name of the task
     * @param oldStatus The old status
     * @param newStatus The new status
     */
    void update(String taskName, TaskStatus oldStatus, TaskStatus newStatus);
}
