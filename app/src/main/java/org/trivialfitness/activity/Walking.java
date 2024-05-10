package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;
import org.trivialfitness.user.User;

public class Walking extends DistanceActivity {

    public Walking(int distanceKm) {
        super("Walking", "Distance", true, distanceKm, 8);
    }

    @Override
    public Walking copy() {
        return new Walking(getDistanceKm());
    }

    @Override
    public double calculateCalories(User user) {
        double prevCalories = super.calculateCalories(user);
        return prevCalories * user.calculateFitnessMultiplier();
    }
}
