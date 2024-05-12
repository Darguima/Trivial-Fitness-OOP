package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.WeightRepetitionsActivity;

public class Deadlift extends WeightRepetitionsActivity {

	public Deadlift(int repetitions, int weightKg) {
		super("Deadlift", "Repetitions with Weight", true, repetitions, 0.75, weightKg, 0.02);
	}

	@Override
	public Deadlift copy() {
		return new Deadlift(getRepetitions(), getWeightKg());
	}

}
