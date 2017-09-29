package de.giorgio_gross.gol.elements;

import de.giorgio_gross.gol.App;
import processing.core.PApplet;

/**
 * Abstract representation of a view. Views will be rendered on the screen
 */
public abstract class View {
    private int x;
    private int y;
    private int width;
    private int height;

    private PApplet context;

    public View(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        context = App.getInstance();
    }

    public PApplet getContext() {
        return context;
    }

    /**
     * Render the view. Implemented by subclasses.
     */
    public abstract void render();

    public boolean onClick(int x, int y) {
        return (x <= this.x + this.width && x >= this.x
                && y >= this.y && y <= this.y + this.height);
    }

}
