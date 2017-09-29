package de.giorgio_gross.gol;

import de.giorgio_gross.gol.data.CellStateManager;
import de.giorgio_gross.gol.elements.Element;
import de.giorgio_gross.gol.elements.OnClickListener;
import de.giorgio_gross.gol.elements.cell.Cell;
import de.giorgio_gross.gol.elements.cell.CellView;
import de.giorgio_gross.gol.elements.environment.Environment;
import de.giorgio_gross.gol.elements.environment.EnvironmentView;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Root class for the Game of Live App. Here happens initialization, kick off of rendering, management of UI's and
 * delegation of sprite rendering.
 *
 * @author Giorgio
 */
public class App extends PApplet implements EventProvider<OnClickListener> {
    /* global variables */
    public static final int NUM_COLUMNS = 20;
    public static final int NUM_ROWS = 10 + 1; // +1 for sun and moon simulation
    public static final int MS_PER_CYCLE = 6000;  // 3s day, 3s night
    private static App instance;

    /* private data */
    private ArrayList<OnClickListener> clickListeners;
    private ArrayList<Element> sceneElements;
    private Environment env;
    private CellStateManager csManager;

    /**
     * Don't fully implement singleton pattern with private constructor to allow PApplet to instantiate App.
     * Yet, there should only be one App instance!
     */
    public App() {
        if (App.instance != null) throw new IllegalAccessError();
        App.instance = this;
    }

    /**
     * Get an instance of the PApplet context to allow all components drawing on the screen
     *
     * @return active PApplet instance
     */
    public static PApplet getInstance() {
        return App.instance;
    }

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
        // frameRate(DESIRED_FPS);  // unstable on macOS
        // fullScreen();  // buggy on retina displays
        size(displayWidth / 3 * 2, displayHeight / 3 * 2);
        // pixelDensity(2);  // for retina displays, poor fps

        initDataStructures();
    }

    private void initDataStructures() {
        // create and bind environment to its view
        EnvironmentView envView = new EnvironmentView(0, 0, width, height);
        env = new Environment(envView);
        envView.setEnvironment(env);

        // init cell state manager
        csManager = new CellStateManager();
        env.register(csManager);

        sceneElements = new ArrayList<Element>();
        clickListeners = new ArrayList<OnClickListener>();

        // init cells
        int cellHeight = height / App.NUM_ROWS;
        int cellWidth = width / App.NUM_COLUMNS;
        for (int idx_x = 0; idx_x < NUM_COLUMNS; idx_x++) {
            for (int idx_y = 0; idx_y < NUM_ROWS - 1; idx_y++) {
                // create and bind cell to its view
                CellView cv = new CellView(idx_x * cellWidth, (idx_y + 1) * cellHeight, cellWidth, cellHeight);
                Cell c = new Cell(cv, csManager, idx_x, idx_y);
                cv.setCell(c);

                env.register(cv);  // environment listener
                this.register(c);  // click listener

                sceneElements.add(c);
            }
        }
    }

    @Override
    public void setup() {
        background(0);
    }

    @Override
    public void draw() {
        // firstly, render the environment
        env.executeLoopCycle();

        // now render all other objects on the screen
        for (Element e : sceneElements) {
            e.executeLoopCycle();
        }
    }

    @Override
    public void mouseClicked() {
        for (OnClickListener cl : clickListeners) {
            if (cl.onClick(mouseX, mouseY)) {
                return;
            }
        }
    }

    @Override
    public void register(OnClickListener eventListener) {
        clickListeners.add(eventListener);
    }
}
