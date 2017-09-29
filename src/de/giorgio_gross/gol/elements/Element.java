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

/**
 * Element which has a view, logic and click listener.
 */
public abstract class Element implements OnClickListener {
    private View view;

    public Element(View view) {
        this.view = view;
    }

    /**
     * Perform all activity associated with this element
     */
    public void executeLoopCycle() {
        transferState();
        view.render();
    }

    /**
     * Transfer/update the internal state of the object
     */
    protected abstract void transferState();

    @Override
    public boolean onClick(int x, int y) {
        return view.onClick(x, y);
    }

}
