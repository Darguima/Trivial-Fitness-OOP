package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

// flexoes
public class PushUps extends Activity {

	private int repetitions; // number of repetitions

	public PushUps(int durationInMinutes, int averageHeartRate, int repetitions, LocalDate date) {
		// Finished Activity constructor
		super(durationInMinutes, averageHeartRate, date);
		this.repetitions = repetitions;
	}

	public PushUps(LocalDate date, int durationInMinutes) {
		// Scheduled Activity constructor
		super(date, durationInMinutes);
		this.repetitions = 0;
	}

	public PushUps(DayOfWeek day, int repetitions) {
		// Training Plan Activity constructor
		super(day);
		this.repetitions = repetitions; // random value
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	@Override
	public PushUps copy(LocalDate datenow) {
		if (this.isCompleted(datenow)) {
			return new PushUps(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions,
					this.getDate());
		}
		else {
			if (this.getDate() != null) {
				return new PushUps(this.getDate(), this.getDurationInMinutes());
			}
			else {
				return new PushUps(this.getDay(), this.getRepetitions());
			}
		}
	}

	@Override
	public double calculateCalories(User user, LocalDate datenow) {

		if (!this.isCompleted(datenow)) {
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
