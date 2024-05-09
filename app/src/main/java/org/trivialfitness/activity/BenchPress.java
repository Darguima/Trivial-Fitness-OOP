package org.trivialfitness.activity;

public class BenchPress extends WeightRepetitionsActivity {

	public BenchPress(int repetitions, int weightKg) {
		super("Bench Press", true, repetitions, 0, weightKg, 0);
	}

	@Override
	public BenchPress copy() {
		return new BenchPress(getRepetitions(), getWeightKg());
	}

}
