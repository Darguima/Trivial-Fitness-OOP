package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;

public class Scissors extends RepetitionsActivity {

	public Scissors(int repetitions) {
		super("Scissors", false, repetitions, 0.05);
	}

	@Override
	public Scissors copy() {

		return new Scissors(this.getRepetitions());

	}

}
