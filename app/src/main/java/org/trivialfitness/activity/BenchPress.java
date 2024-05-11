package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.WeightRepetitionsActivity;

public class BenchPress extends WeightRepetitionsActivity {

	public BenchPress(int repetitions, int weightKg) {
		super("Bench Press", true, repetitions, 5, weightKg, 0.01);
	}

	@Override
	public BenchPress copy() {
		return new BenchPress(getRepetitions(), getWeightKg());
	}

}
