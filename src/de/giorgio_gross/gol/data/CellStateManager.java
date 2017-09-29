package de.giorgio_gross.gol.data;

import de.giorgio_gross.gol.App;
import de.giorgio_gross.gol.elements.environment.EnvironmentListener;

/**
 * Stores, maintains and transfers the state of each cell. Game of Life roules are implemented in here.
 */
public class CellStateManager implements EnvironmentListener {
    private int[][] states;

    public CellStateManager() {
        states = new int[App.NUM_COLUMNS][App.NUM_ROWS];  // filled with 0

        states[10][10] = 1; // todo remove...
    }

    public CellState getState(int x, int y) {
        if (x >= states.length || y >= states[0].length) throw new IllegalArgumentException();
        return states[x][y] == 0 ? CellState.DEAD : CellState.ALIVE;
    }


    @Override
    public void onMidnight() {
        // todo
        // count alive neighbours of each cell

        // set cell state based on number of neighbours
    }

    @Override
    public void onMorning() {
        // no action
    }

    @Override
    public void onEvening() {
        // no action
    }
}
