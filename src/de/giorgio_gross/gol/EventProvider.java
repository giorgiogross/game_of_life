package de.giorgio_gross.gol;

/**
 * Event provider to be reused by several observers
 */
public interface EventProvider<E> {

    /**
     * Registers the passed event listener. References to that object are stored and called upon events.
     * @param eventListener
     */
    void register(E eventListener);

}
