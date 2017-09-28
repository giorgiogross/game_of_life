package de.giorgio_gross.gol.elements;

/**
 * Created by Giorgio on 28.09.17.
 */
public interface OnClickListener {

    /**
     * Process click events.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if element was clicked
     */
    boolean onClick(int x, int y);

}
