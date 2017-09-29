package de.giorgio_gross.gol.elements.cell;

import de.giorgio_gross.gol.data.CellState;
import de.giorgio_gross.gol.data.CellStateManager;
import de.giorgio_gross.gol.elements.Element;
import de.giorgio_gross.gol.elements.environment.EnvironmentListener;

/**
 * Class which performs the logic required for one cell. E.g. fetching the cell state
 */
public class Cell extends Element implements EnvironmentListener {
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

    @Override
    public void onMidnight() {
        // no action
    }

    @Override
    public void onMorning() {

    }

    @Override
    public void onEvening() {

    }

    protected boolean isAlive() {
        return state == CellState.ALIVE;
    }
}
