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

package de.giorgio_gross.gol.elements;

import de.giorgio_gross.gol.App;
import processing.core.PApplet;

/**
 * Abstract representation of a view. Views will be rendered on the screen.
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PApplet getContext() {
        return context;
    }

    /**
     * Render the view. Implemented by subclasses.
     */
    public abstract void render();

    public boolean onClick(int x, int y) {
        if (x <= this.x + this.width && x >= this.x
                && y >= this.y && y <= this.y + this.height) {
            performClickAction();
            return true;
        }
        return false;
    }

    protected abstract void performClickAction();
}
