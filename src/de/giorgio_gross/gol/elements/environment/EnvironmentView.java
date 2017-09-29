package de.giorgio_gross.gol.elements.environment;

import de.giorgio_gross.gol.App;
import de.giorgio_gross.gol.elements.View;

/**
 * View to show the environment on screen.
 */
public class EnvironmentView extends View {
    private int day_cycle_width;
    private int sun_moon_distance;
    private int zenith;
    private final int simulationHeight;
    private int cellHeight;
    private int cellWidth;

    private Environment env;

    public EnvironmentView(int x, int y, int width, int height) {
        super(x, y, width, height);

        simulationHeight = height / App.NUM_ROWS;
        cellHeight = height / App.NUM_ROWS;
        cellWidth = width / App.NUM_COLUMNS;

        zenith = width / 2;
        sun_moon_distance = width;
        day_cycle_width = 2 * width;
    }

    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public void render() {
        float dayProgress = env.getDayProgression();

        int moonPos = ((int) (dayProgress * day_cycle_width) + zenith) % day_cycle_width;
        int sunPos = (moonPos + sun_moon_distance) % day_cycle_width;

        getContext().background(0);
        drawMoon(moonPos);
        drawSun(sunPos);
        drawZenithMarker();
        drawGrid();

        getContext().textSize(32);
        getContext().text(env.getDayCount().toString(), 10, 30);
    }

    private void drawGrid() {
        getContext().noFill();
        getContext().stroke(ColorManager.GetAccentLight().getRGB());
        // vertical
        for(int i = 1; i < App.NUM_COLUMNS; i++) {
            getContext().line(i * cellWidth, simulationHeight, i * cellWidth, getContext().height);
        }
        // horizontal
        for(int i = 1; i < App.NUM_ROWS; i++) {
            getContext().line(0, i * cellHeight, getContext().width, i * cellHeight);
        }
    }

    private void drawZenithMarker() {
        getContext().noFill();
        getContext().stroke(ColorManager.GetAccentLight().getRGB());
        getContext().line(zenith, simulationHeight / 2 - 10, zenith, simulationHeight / 2 + 10);
    }

    private void drawSun(int x) {
        getContext().fill(ColorManager.GetSunColor().getRGB());
        getContext().ellipse(x, simulationHeight / 2, simulationHeight / 4, simulationHeight / 4);
    }

    private void drawMoon(int x) {
        getContext().fill(ColorManager.GetMoonColor().getRGB());
        getContext().ellipse(x, simulationHeight / 2, simulationHeight / 4, simulationHeight / 4);
    }
}
