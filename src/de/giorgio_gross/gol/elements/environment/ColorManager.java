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
