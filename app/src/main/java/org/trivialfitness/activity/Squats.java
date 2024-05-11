package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;

public class Squats extends RepetitionsActivity {

	public Squats(int repetitions) {
		super("Squats", false, repetitions, 0.04);
	}

	@Override
	public Squats copy() {

		return new Squats(this.getRepetitions());

	}

}
