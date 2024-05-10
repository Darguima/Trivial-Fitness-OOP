package org.trivialfitness.activity;

public class JumpingJacks extends RepetitionsActivity {

	public JumpingJacks(int repetitions) {
		super("Jumping Jacks", false, repetitions, 0.03);
	}

	@Override
	public JumpingJacks copy() {
		return new JumpingJacks(this.repetitions);
	}

}
