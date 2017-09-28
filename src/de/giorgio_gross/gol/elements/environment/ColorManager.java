package de.giorgio_gross.gol.elements.environment;

import java.awt.*;

/**
 * Created by Giorgio on 28.09.17.
 */
public class ColorManager {
    private Color lineLight;
    private Color lineDark;
    private Color accentLight;
    private Color accentDark;

    public ColorManager() {
        
    }

    public Color getLineLight() {
        return lineLight;
    }

    public Color getLineDark() {
        return lineDark;
    }

    public Color getAccentLight() {
        return accentLight;
    }

    public Color getAccentDark() {
        return accentDark;
    }

    protected void updatePalette() {

    }

}
