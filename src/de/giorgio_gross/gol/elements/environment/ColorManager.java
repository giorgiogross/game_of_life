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

package de.giorgio_gross.gol.elements.environment;

import java.awt.*;

/**
 * Contains information about the color palette to be used by all rendered elements on the screen.
 */
public final class ColorManager {
    private static Color lineLight = new Color(0, 0, 0, 100);
    private static Color lineDark = new Color(0, 0, 0, 255);
    private static Color accentLight = new Color(51, 153, 255, 255);
    private static Color accentDark = new Color(0, 51, 102, 255);
    private static Color background = new Color(255, 255, 255, 255);
    private static Color white = new Color(255, 255, 255, 255);
    private static Color black = new Color(0, 0, 0, 255);

    private static Color sunColor = new Color(255, 204, 0, 255);
    private static Color moonColor = white;

    public static Color GetLineLight() {
        return lineLight;
    }

    public static Color GetLineDark() {
        return lineDark;
    }

    public static Color GetAccentLight() {
        return accentLight;
    }

    public static Color GetAccentDark() {
        return accentDark;
    }

    public static Color GetWhite() {
        return white;
    }

    public static Color GetBlack() {
        return black;
    }

    public static Color GetSunColor() {
        return sunColor;
    }

    public static Color GetMoonColor() {
        return moonColor;
    }

    public static Color GetBackground() {
        return background;
    }

    protected static void UpdatePalette(float timeProgression) {
        int val = (int) (102 * (-1) * Math.cos(timeProgression * 2 * Math.PI)) + 152;  // simulate darkness and light
        background = new Color(val, val, val, 255);
    }

}
