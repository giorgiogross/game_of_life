package de.giorgio_gross.gol;

/**
 * Gets notified about changes in settings (e.g. duration of one day)
 */
public interface OnSettingsChangedListener {

    /**
     * Ms per day cycle changed
     *
     * @param msPerDay new ms per day cycle
     */
    void onSetDayCycle(int msPerDay);

}
