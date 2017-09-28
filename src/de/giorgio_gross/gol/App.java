package de.giorgio_gross.gol;

import de.giorgio_gross.gol.elements.OnClickListener;
import processing.core.PApplet;

/**
 * Root class for the Game of Live App. Here happens initialization, kick off of rendering, management of UI's and
 * delegation of sprite rendering.
 *
 *
 * @author Giorgio
 */
public class App extends PApplet implements EventProvider<OnClickListener>{

    /**
     * Starts up the app
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        PApplet.main("de.giorgio_gross.gol.App", args);
    }

    @Override
    public void settings() {
        fullScreen();
        pixelDensity(2);  // for retina displays
    }

    @Override
    public void setup() {
        background(0);
    }

    @Override
    public void draw() {

    }

    @Override
    public void keyTyped() {

    }

    @Override
    public void mouseClicked() {

    }

    @Override
    public void register(OnClickListener eventListener) {

    }
}
