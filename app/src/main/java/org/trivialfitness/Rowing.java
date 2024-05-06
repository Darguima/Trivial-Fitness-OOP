package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Rowing extends Activity {

	private double distance; // distance in kilometers

	/**
	 * Create a finished Rowing Activity
	 */
	public Rowing(int durationInMinutes, LocalDate realizationDate, int averageHeartRate, double distance) {
		super(durationInMinutes, realizationDate, averageHeartRate);
		this.distance = distance;
	}

	/**
	 * Create an individual scheduled Rowing Activity
	 */
	public Rowing(int durationInMinutes, LocalDate realizationDate) {
		super(durationInMinutes, realizationDate);
		this.distance = -1;
	}

	/**
	 * Create a Training Plan weekly scheduled Rowing Activity
	 */
	public Rowing(DayOfWeek weekDay, double distance) {
		super(weekDay);
		this.distance = distance; // random value
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public Rowing copy(LocalDate dateNow) {

		if (this.isCompleted(dateNow)) {
			return new Rowing(this.getDurationInMinutes(), this.getDate(), this.getAverageHeartRate(), this.distance);
		}
		else {
			if (this.getDate() != null) {
				return new Rowing(this.getDurationInMinutes(), this.getDate());
			}
			else {
				return new Rowing(this.getDay(), this.getDistance());
			}
		}
	}

	// checking if the day of the activity is before the current day and if so, we
	// set the
	// activity to completed and return true

	@Override
	public double calculateCalories(User user, LocalDate dateNow) {
		if (!this.isCompleted(dateNow)) {
			return 0;
		}

		return (distance * 30 + this.getDurationInMinutes() * 0.5) * user.calculateFitnessMultiplier();
	}

	@Override
	public void scheduledToCompleted() {
		this.updateActivity(10, 100);
		this.setDistance(10);
		// Random values, just to test the method
	}

}
