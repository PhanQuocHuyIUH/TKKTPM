package bai1.task;

/**
 * Task Subject interface
 * Task subjects implement this interface to manage observers
 */
public interface ITaskSubject {
    /**
     * Attach an observer to the task
     * @param observer The observer to attach
     */
    void attach(ITaskObserver observer);
    
    /**
     * Detach an observer from the task
     * @param observer The observer to detach
     */
    void detach(ITaskObserver observer);
    
    /**
     * Notify all attached observers of status change
     */
    void notifyObservers();
}
