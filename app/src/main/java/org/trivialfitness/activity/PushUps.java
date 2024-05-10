package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;
import org.trivialfitness.user.User;

public class PushUps extends RepetitionsActivity {

	public PushUps(int repetitions) {
		super("Push Ups", false, repetitions, 0.11);
	}

	@Override
	public PushUps copy() {
		return new PushUps(this.getRepetitions());
	}

	@Override
	public double calculateCalories(User user) {
		double prevCalories = super.calculateCalories(user);
		double weightLifted = user.getWeight() * 0.64;

		return prevCalories * weightLifted * user.calculateFitnessMultiplier();
	}

}
