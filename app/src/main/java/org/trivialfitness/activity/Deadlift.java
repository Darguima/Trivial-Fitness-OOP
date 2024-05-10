package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.WeightRepetitionsActivity;
import org.trivialfitness.user.User;

public class Deadlift extends WeightRepetitionsActivity {

    public Deadlift(int repetitions, int weightKg) {
        super("Deadlift", "Repetitions with Weight", true, repetitions, 1.0, weightKg, 0.02);
    }

    @Override
    public Deadlift copy() {
        return new Deadlift(getRepetitions(), getWeightKg());
    }

    @Override
    public double calculateCalories(User user) {
        double prevCalories = super.calculateCalories(user);
        double weightLifted = getWeightKg() * 0.75; // Utilizando 75% do peso para simular esforço

        return prevCalories * weightLifted * user.calculateFitnessMultiplier();
    }
}
