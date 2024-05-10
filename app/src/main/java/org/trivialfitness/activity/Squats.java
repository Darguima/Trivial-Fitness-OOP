package org.trivialfitness.activity;

public class Squats extends RepetitionsActivity {

	public Squats(int repetitions) {
		super("Squats", false, repetitions, 0.04);
	}

	@Override
	public PushUps copy() {
		return new PushUps(this.repetitions);
	}

}
