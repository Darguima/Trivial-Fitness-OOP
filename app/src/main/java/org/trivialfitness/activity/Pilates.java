package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.Activity;
import org.trivialfitness.user.User;



public class Pilates extends Activity {

    public Pilates() {
        super("Pilates", "Flexibility", false);
    }

    @Override
    public double calculateCalories(User user) {
        return 30 * user.calculateFitnessMultiplier(); 
    }

    @Override
    public String getActivityAttributesString() {
        return "Pilates session";
    }

    @Override
    public Pilates copy() {
        return new Pilates();
    }
}
