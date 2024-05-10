package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.Activity;
import org.trivialfitness.user.User;


public class Yoga extends Activity {

    public Yoga() {
        super("Yoga", "Flexibility", false);
    }

    @Override
    public double calculateCalories(User user) {
        return 0.5 * user.calculateFitnessMultiplier(); 
    }

    @Override
    public String getActivityAttributesString() {
        return "Yoga session"; //
    }

    @Override
    public Yoga copy() {
        return new Yoga();
    }
}
