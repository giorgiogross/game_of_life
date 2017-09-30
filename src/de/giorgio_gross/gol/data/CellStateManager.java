/**
 * MIT License
 * <p>
 * Copyright (c) 2017 Giorgio Groß
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.giorgio_gross.gol.data;

import de.giorgio_gross.gol.App;
import de.giorgio_gross.gol.elements.environment.EnvironmentListener;

/**
 * Stores, maintains and transfers the state of each cell. Game of Life rules are implemented in here.
 */
public class CellStateManager implements EnvironmentListener {
    private final int DEAD = 0;
    private final int ALIVE = 1;

    private int[][] states;

    public CellStateManager() {
        states = new int[App.NUM_COLUMNS][App.NUM_ROWS - 1];  // filled with 0

        int midX = App.NUM_COLUMNS / 2 - 1;
        int midY = App.NUM_ROWS / 2 - 1;

        if (midX > 2 && midY > 2) {
            // initialize sample (Small Exploder)
            states[midX][midY - 1] = ALIVE;
            states[midX][midY] = ALIVE;
            states[midX - 1][midY] = ALIVE;
            states[midX - 1][midY + 1] = ALIVE;
            states[midX + 1][midY] = ALIVE;
            states[midX + 1][midY + 1] = ALIVE;
            states[midX][midY + 2] = ALIVE;
        }

    }

    public CellState getState(int x, int y) {
        if (x >= states.length || y >= states[0].length) throw new IllegalArgumentException();
        return states[x][y] == DEAD ? CellState.DEAD : CellState.ALIVE;
    }


    @Override
    public void onMidnight() {
        // count alive neighbours of each cell

        final int ALIVE_ZERO_NBRS = 9;  // // represents 0 neighbours of a living cell

        for (int x = 0; x < states.length; x++) {
            for (int y = 0; y < states[0].length; y++) {
                // iterate from north-west to south-east neighbour and count how many of them are alive
                int xs = (x - 1) % states.length;
                if (xs < 0) xs += states.length;
                int ys = (y - 1) % states[0].length;
                if (ys < 0) ys += states[0].length;
                int nbrsSum = 0;
                boolean negativeSum = states[x][y] == DEAD;  // save negative sum of neighbors for dead cells

                for (int offX = 0; offX < 3; offX++) {
                    for (int offY = 0; offY < 3; offY++) {
                        if (offX == 1 && offY == 1)
                            continue;  // skip center of the 3x3 sub array we are looking at here

                        // add +1 for each living neighbour to counter of living cells or -1 to counter of dead cells
                        if (negativeSum)
                            nbrsSum -= (states[(xs + offX) % states.length][(ys + offY) % states[0].length] > 0) ? 1 : 0;
                        else
                            nbrsSum += (states[(xs + offX) % states.length][(ys + offY) % states[0].length] > 0) ? 1 : 0;
                    }
                }
                if (nbrsSum == 0 && states[x][y] == ALIVE) nbrsSum = ALIVE_ZERO_NBRS;
                states[x][y] = nbrsSum;
            }
        }

        // set cell state based on number of neighbours
        for (int x = 0; x < states.length; x++) {
            for (int y = 0; y < states[0].length; y++) {
                if (states[x][y] == ALIVE_ZERO_NBRS) states[x][y] = 0; // override with 0 neighbours

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
        states[x][y] = ALIVE;
    }

    public void kill(int x, int y) {
        if (x >= states.length || y >= states[0].length) throw new IllegalArgumentException();
        states[x][y] = DEAD;
    }
}
