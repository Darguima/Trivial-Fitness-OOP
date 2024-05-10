package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;

public class IndoorCycling extends DistanceActivity {

    public IndoorCycling(int distanceKm) {
        super("Indoor Cycling", "Distance", true, distanceKm, 8);
    }

    @Override
    public IndoorCycling copy() {
        return new IndoorCycling(getDistanceKm());
    }
}
 