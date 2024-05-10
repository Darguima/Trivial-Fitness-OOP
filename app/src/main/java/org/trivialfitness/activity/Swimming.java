package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;
import org.trivialfitness.user.User;

public class Swimming extends DistanceActivity {

    public Swimming(int distanceKm) {
        super("Swimming", "Water", true, distanceKm, 12);
    }

    @Override
    public Swimming copy() {
        return new Swimming(getDistanceKm());
    }

    @Override
    public double calculateCalories(User user) {
        return 40 * user.calculateFitnessMultiplier(); 
    }
}
