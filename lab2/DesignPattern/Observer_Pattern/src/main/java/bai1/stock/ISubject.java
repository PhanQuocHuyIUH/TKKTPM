package bai1.stock;

/**
 * Subject interface for the Observer pattern
 * Subjects implement this interface to manage observers
 */
public interface ISubject {
    /**
     * Attach an observer to the subject
     * @param observer The observer to attach
     */
    void attach(IObserver observer);
    
    /**
     * Detach an observer from the subject
     * @param observer The observer to detach
     */
    void detach(IObserver observer);
    
    /**
     * Notify all attached observers of state change
     */
    void notifyObservers();
}
