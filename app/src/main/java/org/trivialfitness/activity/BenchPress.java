package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public class BenchPress extends Activity {

	private int repetitions;

	private int weight;

	public BenchPress(int repetitions, int weight) {
		super("Bench Press", ActivityType.REPETITIONS_WITH_WEIGHT, false);
		this.repetitions = repetitions;
		this.weight = weight;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public BenchPress copy() {
		return new BenchPress(this.repetitions, this.weight);
	}

	@Override
	public double calculateCalories(User user) {
		double caloriesPerRep = 0.03;
		return (repetitions * weight * caloriesPerRep) * user.calculateFitnessMultiplier();
	}

}
