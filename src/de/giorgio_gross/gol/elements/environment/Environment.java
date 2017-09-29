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

import de.giorgio_gross.gol.App;
import de.giorgio_gross.gol.EventProvider;
import de.giorgio_gross.gol.elements.Element;

import java.util.ArrayList;

/**
 * Class which performs the logic required for the environment. E.g. performing the day cycle.
 */
public class Environment extends Element implements EventProvider<EnvironmentListener> {
    private ArrayList<EnvironmentListener> listeners;
    private boolean isBroadcastMorning = false;
    private boolean isBroadcastEvening = false;
    private int dayCount = 0;

    private final long startTime;
    private long offsetTime;  // offset between start time in ms and last number which can be divided by MS_PER_CYCLE
    private float dayProgression;  // day time in percent

    public Environment(EnvironmentView envView) {
        super(envView);

        listeners = new ArrayList<EnvironmentListener>();
        startTime = System.currentTimeMillis();
        offsetTime = startTime % App.MS_PER_CYCLE;
        dayProgression = 0f;  // 0% of day passed
    }

    float getDayProgression() {
        return dayProgression;
    }

    @Override
    protected void transferState() {
        // perform the day cycle based on time span for one day, independent of varying fps of machine
        long cTime = System.currentTimeMillis();
        long curCycleTime = (cTime - offsetTime) % App.MS_PER_CYCLE;
        dayProgression = (float) curCycleTime / (float) App.MS_PER_CYCLE;
        ColorManager.UpdatePalette(dayProgression);

        // check if a new day began while tolerating differences in fps
        int updDayCount = (int) ((cTime - startTime) / App.MS_PER_CYCLE);
        if (updDayCount > dayCount) {
            // a new day began!

            // inform listeners
            for (EnvironmentListener l : listeners) l.onMidnight();

            // reset all day cycle variables
            dayCount = updDayCount;
            isBroadcastEvening = false;
            isBroadcastMorning = false;

            return;
        }

        // inform listeners about events once they occur
        if (dayProgression >= 0.25f && !isBroadcastMorning) {
            for (EnvironmentListener l : listeners) l.onMorning();
            isBroadcastMorning = true;
        } else if (dayProgression >= 0.75f && !isBroadcastEvening) {
            for (EnvironmentListener l : listeners) l.onEvening();
            isBroadcastEvening = true;
        }
    }

    @Override
    public void register(EnvironmentListener eventListener) {
        listeners.add(eventListener);
    }

    Integer getDayCount() {
        return dayCount;
    }
}
