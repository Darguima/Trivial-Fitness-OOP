package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;
import org.trivialfitness.user.User;

public class PushUps extends RepetitionsActivity {

	private static double caloriesPerUserKg = 0.01;

	public PushUps(int repetitions) {
		super("Push Ups", false, repetitions, 0.11);
	}

	@Override
	public PushUps copy() {
		return new PushUps(this.getRepetitions());
	}

	@Override
	public double calculateCalories(User user) {
		double repetitionCalories = super.calculateCalories(user);

		return repetitionCalories + (user.getWeight() * caloriesPerUserKg * user.calculateFitnessMultiplier());
	}

	@Override
	public void setActivityAttributesWithCaloryGoal(User user, int caloriesGoal) {
		caloriesGoal -= user.getWeight() * caloriesPerUserKg * user.calculateFitnessMultiplier();
		super.setActivityAttributesWithCaloryGoal(user, caloriesGoal + 1);
	}

}
