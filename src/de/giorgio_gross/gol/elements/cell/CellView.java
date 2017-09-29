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

import de.giorgio_gross.gol.elements.View;
import de.giorgio_gross.gol.elements.environment.ColorManager;
import de.giorgio_gross.gol.elements.environment.EnvironmentListener;
import processing.core.PApplet;

import java.util.Random;

/**
 * View to show one cell on the screen.
 */
public class CellView extends View implements EnvironmentListener {
    private Cell cell;
    private int awakeJitter = 0;  // render calls until we wake up
    private int sleepJitter = 0;  // render calls until we fall asleep
    private Random rand;

    public CellView(int x, int y, int width, int height) {
        super(x, y, width, height);
        rand = new Random();
    }

    @Override
    public void render() {
        if (!cell.isAlive()) return;

        drawEyes();
        drawMouth();

        if (awakeJitter > 0) awakeJitter--;
        if (sleepJitter > 0) sleepJitter--;
    }

    private void drawMouth() {
        getContext().noFill();
        getContext().stroke(ColorManager.GetBlack().getRGB());
        getContext().arc(getX() + getWidth() / 2, getY() + getHeight() / 2, 2 * getHeight() / 3, 2 * getHeight() / 3, 0, PApplet.PI);
    }

    private void drawEyes() {
        if (awakeJitter == 0 || sleepJitter > 0) {
            getContext().fill(ColorManager.GetWhite().getRGB());
            getContext().stroke(ColorManager.GetBlack().getRGB());

            getContext().ellipse(getX() + getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, getWidth() / 4);
            getContext().ellipse(getX() + 2 * getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, getWidth() / 4);
            getContext().fill(ColorManager.GetBlack().getRGB());
            getContext().ellipse(getX() + getWidth() / 3, getY() + getHeight() / 3, 3, 3);
            getContext().ellipse(getX() + 2 * getWidth() / 3, getY() + getHeight() / 3, 3, 3);
        } else if (sleepJitter == 0 || awakeJitter > 0) {
            getContext().stroke(ColorManager.GetBlack().getRGB());
            getContext().ellipse(getX() + getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, 1);
            getContext().ellipse(getX() + 2 * getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, 1);
        }
    }

    @Override
    protected void performClickAction() {
        cell.toggleState();

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
        if (cell.isAlive()) {
            awakeJitter = rand.nextInt(30);  // don't wake up simultaneously..
        } else {
            awakeJitter = 0;
        }
        sleepJitter = -1;
    }

    @Override
    public void onEvening() {
        if (cell.isAlive()) {
            sleepJitter = rand.nextInt(30);  // don't fall asleep simultaneously..
        } else {
            sleepJitter = 0;
        }
        awakeJitter = -1;
    }
}
