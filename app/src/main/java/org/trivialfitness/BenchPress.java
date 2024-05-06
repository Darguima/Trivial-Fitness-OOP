package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BenchPress extends Activity {

	private int repetitions; // number of repetitions

	private double weight; // weight in kilograms

	/**
	 * Create a finished BenchPress Activity
	 */
	public BenchPress(int durationInMinutes, LocalDate realizationDate, int averageHeartRate, int repetitions,
			double weight) {
		super(durationInMinutes, realizationDate, averageHeartRate);
		this.repetitions = repetitions;
		this.weight = weight;
	}

	/**
	 * Create an individual scheduled Bench Press Activity
	 */
	public BenchPress(int durationInMinutes, LocalDate realizationDate) {
		super(durationInMinutes, realizationDate);
		this.repetitions = -1;
		this.weight = -1;
	}

	/**
	 * Create a Training Plan weekly scheduled Bench Press Activity
	 */
	public BenchPress(DayOfWeek weekDay, int repetitions, double weight) {
		// Training Plan Activity constructor
		super(weekDay);
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
	public BenchPress copy(LocalDate dateNow) {
		if (this.isCompleted(dateNow)) {
			return new BenchPress(this.getDurationInMinutes(), this.getDate(), this.getAverageHeartRate(),
					this.repetitions, this.weight);
		}
		else {
			if (this.getDate() != null) {
				return new BenchPress(this.getDurationInMinutes(), this.getDate());
			}
			else {
				return new BenchPress(this.getDay(), this.getRepetitions(), this.getWeight());
			}
		}
	}

	@Override
	public double calculateCalories(User user, LocalDate dateNow) {
		if (!this.isCompleted(dateNow)) {
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