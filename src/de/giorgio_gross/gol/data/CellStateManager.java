package de.giorgio_gross.gol.data;

import de.giorgio_gross.gol.App;
import de.giorgio_gross.gol.elements.environment.EnvironmentListener;

/**
 * Stores, maintains and transfers the state of each cell. Game of Life roules are implemented in here.
 */
public class CellStateManager implements EnvironmentListener {
    private int[][] states;

    public CellStateManager() {
        states = new int[App.NUM_COLUMNS][App.NUM_ROWS - 1];  // filled with 0

        states[5][5] = 1; // todo remove...
    }

    public CellState getState(int x, int y) {
        if (x >= states.length || y >= states[0].length) throw new IllegalArgumentException();
        return states[x][y] == 0 ? CellState.DEAD : CellState.ALIVE;
    }


    @Override
    public void onMidnight() {
        // count alive neighbours of each cell
        for (int x = 0; x < states.length; x++) {
            for (int y = 0; y < states[0].length; y++) {
                // iterate from north-west to south-east neighbour and count how many of them are alive
                int xs = (x - 1) % states.length;
                if (xs < 0) xs += states.length;
                int ys = (y - 1) % states[0].length;
                if (ys < 0) ys += states[0].length;
                int nbrsSum = 0;
                boolean negativeSum = states[x][y] == 0;  // save negative sum of neighbors for dead cells

                for (int offX = 0; offX < 3; offX++) {
                    for (int offY = 0; offY < 3; offY++) {
                        if (offX == 1 && offY == 1) continue;  // skip center of the 3x3 sub array we are looking at here

                        if (negativeSum)
                            nbrsSum -= (states[(xs + offX) % states.length][(ys + offY) % states[0].length] > 0) ? 1 : 0;
                        else
                            nbrsSum += (states[(xs + offX) % states.length][(ys + offY) % states[0].length] > 0) ? 1 : 0;
                    }
                }
                states[x][y] = nbrsSum;
            }
        }

        // set cell state based on number of neighbours
        for (int x = 0; x < states.length; x++) {
            for (int y = 0; y < states[0].length; y++) {
                if (states[x][y] > 0 && states[x][y] < 2) kill(x, y);
                else if (states[x][y] > 0 && (states[x][y] == 2 || states[x][y] == 3)) revive(x, y);
                else if (states[x][y] > 0 && states[x][y] > 3) kill(x, y);
                else if (states[x][y] < 0 && Math.abs(states[x][y]) == 3) revive(x, y);
                else kill(x, y);  // default to dead
            }
        }
    }

    @Override
    public void onMorning() {
        // no action
    }

    @Override
    public void onEvening() {
        // no action
    }

    public void revive(int x, int y) {
        if (x >= states.length || y >= states[0].length) throw new IllegalArgumentException();
        states[x][y] = 1;
    }

    public void kill(int x, int y) {
        if (x >= states.length || y >= states[0].length) throw new IllegalArgumentException();
        states[x][y] = 0;
    }
}
