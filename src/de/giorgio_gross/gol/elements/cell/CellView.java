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

import java.awt.*;
import java.util.Random;

/**
 * View to show one cell on the screen.
 */
public class CellView extends View implements EnvironmentListener {
    private Cell cell;
    private int awakeJitter = 0;  // render calls until we wake up
    private int sleepJitter = 0;  // render calls until we fall asleep
    private int heartAnimationCycles = 0;  // render calls until heart is faded out
    private int heartAlpha = 255;  // color alpha
    private Random rand;

    public CellView(int x, int y, int width, int height) {
        super(x, y, width, height);
        rand = new Random();
    }

    @Override
    public void render() {
        if (!cell.isAlive()) {
            heartAnimationCycles = 0;
            return;
        }

        drawEyes();
        drawMouth();
        drawHeart();

        if (awakeJitter > 0) awakeJitter--;
        if (sleepJitter > 0) sleepJitter--;
    }

    private void drawHeart() {
        if (heartAnimationCycles == 0) return;
        if (heartAnimationCycles <= 15) heartAlpha -= 17;  // fade out

        Color hc = ColorManager.GetHeartColor();
        getContext().fill(hc.getRed(), hc.getGreen(), hc.getBlue(), heartAlpha);
        getContext().noStroke();
        getContext().alpha(heartAlpha);
        heartAnimationCycles--;

        getContext().ellipse(getX() + getWidth() * 4 / 5, getY() + getHeight() * 4 / 5, 6, 6);
        getContext().ellipse(getX() + getWidth() * 4 / 5 + 6, getY() + getHeight() * 4 / 5, 6, 6);
        getContext().triangle(getX() + getWidth() * 4 / 5 - 3, getY() + getHeight() * 4 / 5,
                getX() + getWidth() * 4 / 5 + 9, getY() + getHeight() * 4 / 5,
                getX() + getWidth() * 4 / 5 + 3, getY() + getHeight() * 4 / 5 + 6);
    }

    private void drawMouth() {
        getContext().noFill();
        getContext().stroke(ColorManager.GetBlack().getRGB());
        getContext().arc(getX() + getWidth() / 2, getY() + getHeight() / 2,
                2 * getHeight() / 3, 2 * getHeight() / 3, 0, PApplet.PI);
    }

    private void drawEyes() {
        if (awakeJitter == 0 || sleepJitter > 0) {
            // opened eyes
            getContext().fill(ColorManager.GetWhite().getRGB());
            getContext().stroke(ColorManager.GetBlack().getRGB());
            getContext().ellipse(getX() + getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, getWidth() / 4);
            getContext().ellipse(getX() + 2 * getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, getWidth() / 4);

            getContext().fill(ColorManager.GetBlack().getRGB());
            getContext().ellipse(getX() + getWidth() / 3, getY() + getHeight() / 3, 3, 3);
            getContext().ellipse(getX() + 2 * getWidth() / 3, getY() + getHeight() / 3, 3, 3);
        } else if (sleepJitter == 0 || awakeJitter > 0) {
            // closed eyes
            getContext().stroke(ColorManager.GetBlack().getRGB());
            getContext().ellipse(getX() + getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, 1);
            getContext().ellipse(getX() + 2 * getWidth() / 3, getY() + getHeight() / 3, getWidth() / 4, 1);
        }
    }

    @Override
    protected void performClickAction() {
        cell.toggleState();

        // trigger heart animation
        heartAnimationCycles = 45;
        heartAlpha = 255;
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
