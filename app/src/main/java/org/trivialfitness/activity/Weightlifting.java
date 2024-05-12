package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.WeightRepetitionsActivity;

public class Weightlifting extends WeightRepetitionsActivity {

	public Weightlifting(int repetitions, int weightKg) {
		super("Weightlifting", "Repetitions with Weight", true, repetitions, 5, weightKg, 2);
	}

	@Override
	public Weightlifting copy() {
		return new Weightlifting(getRepetitions(), getWeightKg());
	}

}
