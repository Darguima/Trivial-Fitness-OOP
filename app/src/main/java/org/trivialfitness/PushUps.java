package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PushUps extends Activity {

	private int repetitions; // number of repetitions

	/**
	 * Create a finished PushUps Activity
	 */
	public PushUps(int durationInMinutes, LocalDate realizationDate, int averageHeartRate, int repetitions) {
		super(durationInMinutes, realizationDate, averageHeartRate);
		this.repetitions = repetitions;
	}

	/**
	 * Create an individual scheduled PushUps Activity
	 */
	public PushUps(int durationInMinutes, LocalDate realizationDate) {
		super(durationInMinutes, realizationDate);
		this.repetitions = -1;
	}

	/**
	 * Create a Training Plan weekly scheduled PushUps Activity
	 */
	public PushUps(DayOfWeek weekDay, int repetitions) {
		super(weekDay);
		this.repetitions = repetitions; // random value
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	@Override
	public PushUps copy(LocalDate dateNow) {
		if (this.isCompleted(dateNow)) {
			return new PushUps(this.getDurationInMinutes(), this.getDate(), this.getAverageHeartRate(),
					this.repetitions);
		}
		else {
			if (this.getDate() != null) {
				return new PushUps(this.getDurationInMinutes(), this.getDate());
			}
			else {
				return new PushUps(this.getDay(), this.getRepetitions());
			}
		}
	}

	@Override
	public double calculateCalories(User user, LocalDate dateNow) {

		if (!this.isCompleted(dateNow)) {
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
