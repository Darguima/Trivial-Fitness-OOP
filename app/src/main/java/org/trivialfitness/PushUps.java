package org.trivialfitness;

import java.time.LocalDate;

// flexoes
public class PushUps extends Activity {

	private int repetitions; // number of repetitions

	public PushUps(int durationInMinutes, int averageHeartRate, int repetitions, LocalDate date) {
		// Finished Activity constructor
		super(durationInMinutes, averageHeartRate, date);
		this.repetitions = repetitions;
	}

	public PushUps(LocalDate date) {
		// Scheduled Activity constructor
		super(date);
		this.repetitions = 0;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	@Override
	public PushUps copy() {
		if (this.isCompleted()) {
			return new PushUps(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions,
					this.getDate());
		}
		else {
			return new PushUps(this.getDate());
		}
	}

	@Override
	public double calculateCalories(User user) {

		if (!this.isCompleted()) {
			return 0;
		}

		double caloriesPerRep = 0.11;
		double weightLifted = user.getWeight() * 0.64;
		return repetitions * weightLifted * caloriesPerRep * user.calculateFitnessMultiplier();
	}

	@Override
	public void scheduledToCompleted() {
		this.updateActivity(10, 100);
		this.setRepetitions(20);
		// Random values, just to test the method
	}

}
