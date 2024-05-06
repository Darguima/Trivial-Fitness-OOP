package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BenchPress extends Activity {

	private int repetitions; // number of repetitions

	private double weight; // weight in kilograms

	public BenchPress(int durationInMinutes, int averageHeartRate, int repetitions, double weight, LocalDate date) {
		// Finished Activity constructor
		super(durationInMinutes, averageHeartRate, date);
		this.repetitions = repetitions;
		this.weight = weight;
	}

	public BenchPress(LocalDate date, int durationInMinutes) {
		// Scheduled Activity constructor
		super(date, durationInMinutes);
		this.repetitions = 0;
		this.weight = 0;
	}

	public BenchPress(DayOfWeek day, int repetitions, double weight) {
		// Training Plan Activity constructor
		super(day);
		this.repetitions = repetitions; // random value
		this.weight = weight; // random value
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public BenchPress copy(LocalDate datenow) {
		if (this.isCompleted(datenow)) {
			return new BenchPress(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions,
					this.weight, this.getDate());
		}
		else {
			if (this.getDate() != null) {
				return new BenchPress(this.getDate(), this.getDurationInMinutes());
			}
			else {
				return new BenchPress(this.getDay(), this.getRepetitions(), this.getWeight());
			}
		}
	}

	@Override
	public double calculateCalories(User user, LocalDate datenow) {
		if (!this.isCompleted(datenow)) {
			return 0;
		}

		return (repetitions * weight * 0.03) * user.calculateFitnessMultiplier();
	}

	@Override

	public void scheduledToCompleted() {
		this.updateActivity(10, 100);
		this.setRepetitions(10);
		this.setWeight(50);

		// Random values, just to test the method
	}

}