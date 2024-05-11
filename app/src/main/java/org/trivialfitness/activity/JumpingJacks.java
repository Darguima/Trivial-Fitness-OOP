package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;

public class JumpingJacks extends RepetitionsActivity {

	public JumpingJacks(int repetitions) {
		super("Jumping Jacks", false, repetitions, 0.03);
	}

	@Override
	public JumpingJacks copy() {

		return new JumpingJacks(this.getRepetitions());

	}

}
