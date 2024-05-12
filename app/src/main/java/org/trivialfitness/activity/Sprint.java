package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;

public class Sprint extends RepetitionsActivity {

	public Sprint(int repetitions) {
		super("Sprint", false, repetitions, 0.01);
	}

	@Override
	public Sprint copy() {
		return new Sprint(this.getRepetitions());
	}

}
