package org.trivialfitness.activity;

public class Burpees extends RepetitionsActivity {

	public Burpees(int repetitions) {
		super("Burpees", false, repetitions, 0.15);
	}

	@Override
	public Burpees copy() {
		return new Burpees(this.repetitions);
	}

}
