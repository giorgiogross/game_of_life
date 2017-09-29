package de.giorgio_gross.gol.elements.cell;

import de.giorgio_gross.gol.elements.View;
import de.giorgio_gross.gol.elements.environment.ColorManager;
import de.giorgio_gross.gol.elements.environment.EnvironmentListener;

/**
 * View to show one cell on the screen.
 */
public class CellView extends View implements EnvironmentListener {
    private Cell cell;

    public CellView(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render() {
        if(cell.isAlive()) {
            getContext().fill(ColorManager.GetAccentDark().getRGB());
            getContext().rect(getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    protected void performClickAction() {
        cell.revive();
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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
}
