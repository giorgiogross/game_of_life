package de.giorgio_gross.gol.elements.environment;

/**
 * Created by Giorgio on 28.09.17.
 */
public interface EnvironmentListener {

    /**
     * Called whenever one day cycle is passed. Triggers the cell states to be transferred to their new states
     */
    void onMidnight();

    /**
     * Called during early day cycle to allow cells to perform animations (wake up, ...)
     */
    void onMorning();

    /**
     * Called during late day cycle to allow cells to perform animations (fall asleep, ...)
     */
    void onEvening();

}
