package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;
import org.trivialfitness.user.User;

public class JumpRope extends RepetitionsActivity {

    public JumpRope(int repetitions) {
        super("Jump Rope", "Repetitions", true, repetitions, 0.1);
    }

    @Override
    public JumpRope copy() {
        return new JumpRope(getRepetitions());
    }

    @Override
    public double calculateCalories(User user) {
        double prevCalories = super.calculateCalories(user);
        return prevCalories * user.calculateFitnessMultiplier(); 
    }
}
