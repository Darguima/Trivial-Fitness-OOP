package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;
import org.trivialfitness.user.User;

public class Boxing extends RepetitionsActivity {

    public Boxing(int repetitions) {
        super("Boxing", "Repetitions", true, repetitions, 0.25);
    }

    @Override
    public Boxing copy() {
        return new Boxing(getRepetitions());
    }

    @Override
    public double calculateCalories(User user) {
        double prevCalories = super.calculateCalories(user);
        return prevCalories * user.calculateFitnessMultiplier(); 
    }
}
