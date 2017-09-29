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

package de.giorgio_gross.gol.elements.cell;

import de.giorgio_gross.gol.data.CellState;
import de.giorgio_gross.gol.data.CellStateManager;
import de.giorgio_gross.gol.elements.Element;

/**
 * Class which performs the logic required for one cell. E.g. fetching the cell state
 */
public class Cell extends Element {
    private CellStateManager csManager;

    private int idx_x;
    private int idx_y;
    private CellState state;

    public Cell(CellView cView, CellStateManager csManager, int idx_x, int idx_y) {
        super(cView);

        this.csManager = csManager;
        this.idx_x = idx_x;
        this.idx_y = idx_y;
    }

    @Override
    protected void transferState() {
        // get the new state of the cell
        state = csManager.getState(idx_x, idx_y);
    }

    boolean isAlive() {
        return state == CellState.ALIVE;
    }

    void toggleState() {
        if (state == CellState.ALIVE) {
            csManager.kill(idx_x, idx_y);
            return;
        }

        csManager.revive(idx_x, idx_y);
    }

}
