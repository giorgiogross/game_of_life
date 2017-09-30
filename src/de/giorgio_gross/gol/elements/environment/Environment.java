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

import de.giorgio_gross.gol.EventProvider;
import de.giorgio_gross.gol.OnSettingsChangedListener;
import de.giorgio_gross.gol.elements.Element;

import java.util.ArrayList;

/**
 * Class which performs the logic required for the environment. E.g. performing the day cycle and informing listeners
 * about morning, midnight, evening.
 */
public class Environment extends Element implements EventProvider<EnvironmentListener>, OnSettingsChangedListener {
    private ArrayList<EnvironmentListener> listeners;
    private boolean isBroadcastMorning = false;
    private boolean isBroadcastEvening = false;
    private int dayCount = 0;  // count depending on ms per day
    private int totalDayCount = 0;  // count of all days passed

    private final long startTime;
    private long offsetTime;  // offset between start time in ms and last number which can be divided by msPerDay
    private float dayProgression = 0f;  // day time in percent
    private int msPerDay;

    public Environment(EnvironmentView envView, int msPerCycle) {
        super(envView);

        listeners = new ArrayList<EnvironmentListener>();
        startTime = System.currentTimeMillis();

        onSetDayCycle(msPerCycle);
    }

    float getDayProgression() {
        return dayProgression;
    }

    @Override
    protected void transferState() {
        // perform the day cycle based on time span for one day, independent of varying fps of machine
        long cTime = System.currentTimeMillis();
        long curCycleTime = (cTime - offsetTime) % msPerDay;
        dayProgression = (float) curCycleTime / (float) msPerDay;
        ColorManager.UpdatePalette(dayProgression);

        // check if a new day began while tolerating differences in fps
        int updDayCount = (int) ((cTime - startTime) / msPerDay);
        if (updDayCount > dayCount) {
            // a new day began!

            // inform listeners
            for (EnvironmentListener l : listeners) l.onMidnight();

            // reset all day cycle variables
            dayCount = updDayCount;
            isBroadcastEvening = false;
            isBroadcastMorning = false;

            totalDayCount++;
            return;
        }

        // inform listeners about events once they occur
        if (dayProgression >= 0.25f && dayProgression < 0.75f && !isBroadcastMorning) {
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

    Integer getTotalDayCount() {
        return totalDayCount;
    }

    @Override
    public void onSetDayCycle(int msPerDay) {
        this.msPerDay = msPerDay;
        offsetTime = startTime % this.msPerDay;
        // dayProgression will be updated during transferState() ...

        dayCount = (int) ((System.currentTimeMillis() - startTime) / msPerDay);
    }
}
