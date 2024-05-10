package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;
import org.trivialfitness.user.User;

public class Running extends DistanceActivity {

    public Running(int distanceKm) {
        super("Running", "Distance", true, distanceKm, 14);
    }

    @Override
    public Running copy() {
        return new Running(getDistanceKm());
    }

    @Override
    public double calculateCalories(User user) {
        double prevCalories = super.calculateCalories(user);
        // Não considerando o peso do user
        return prevCalories * user.calculateFitnessMultiplier();
    }
}
