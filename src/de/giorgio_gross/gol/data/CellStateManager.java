package de.giorgio_gross.gol.data;

import de.giorgio_gross.gol.elements.environment.EnvironmentListener;

/**
 * Stores, maintains and transfers the state of each cell. Game of Life roules are implemented in here.
 */
public class CellStateManager implements EnvironmentListener {
    private int[][] states;

    public CellStateManager(int width, int height) {
        states = new int[width][height];  // filled with 0
    }

    public void getState(int x, int y) {

    }


    @Override
    public void onMidnight() {

    }

    @Override
    public void onMorning() {

    }

    @Override
    public void onEvening() {

    }
}
