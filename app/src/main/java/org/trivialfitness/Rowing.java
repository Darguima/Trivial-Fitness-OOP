package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

// esta atividade representa canoagem
public class Rowing extends Activity {

	private double distance; // distance in kilometers

	public Rowing(int durationInMinutes, int averageHeartRate, double distance, LocalDate date) {
		// Finished Activity constructor
		super(durationInMinutes, averageHeartRate, date);
		this.distance = distance;
	}

	public Rowing(LocalDate date, double distance, int durationInMinutes) {
		// Scheduled Activity constructor
		super(date, durationInMinutes);
		this.distance = distance;
	}

	public Rowing(DayOfWeek day, double distance) {
		// Training Plan Activity constructor
		super(day);
		this.distance = distance; // random value
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public Rowing copy(LocalDate datenow) {

		if (this.isCompleted(datenow)) {
			return new Rowing(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance, this.getDate());
		}
		else {
			if (this.getDate() != null) {
				return new Rowing(this.getDate(), this.distance, this.getDurationInMinutes());
			}
			else {
				return new Rowing(this.getDay(), this.getDistance());
			}
		}
	}

	// checking if the day of the activity is before the current day and if so, we set the
	// activity to completed and return true

	@Override
	public double calculateCalories(User user, LocalDate datenow) {
		if (!this.isCompleted(datenow)) {
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
