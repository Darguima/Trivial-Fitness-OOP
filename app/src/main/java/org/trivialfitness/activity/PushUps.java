package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public class PushUps extends Activity {

	private int repetitions;

	public PushUps(int repetitions) {
		super("PushUps", ActivityType.REPETITIONS, false);
		this.repetitions = repetitions;
	}

	public int getRepetitions() {
		return repetitions;
	}

	@Override
	public PushUps copy() {
		return new PushUps(this.repetitions);
	}

	@Override
	public double calculateCalories(User user) {
		double caloriesPerRep = 0.11;
		double weightLifted = user.getWeight() * 0.64;
		return repetitions * weightLifted * caloriesPerRep * user.calculateFitnessMultiplier();
	}

}
