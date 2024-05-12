package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.RepetitionsActivity;

public class JumpRope extends RepetitionsActivity {

	public JumpRope(int repetitions) {
		super("Jump Rope", "Repetitions", true, repetitions, 0.1);
	}

	@Override
	public JumpRope copy() {
		return new JumpRope(getRepetitions());
	}

}
