package org.trivialfitness;

import java.time.LocalDate;

// esta atividade representa canoagem
public class Rowing extends Activity {

	private double distance; // distance in kilometers

	public Rowing(int durationInMinutes, int averageHeartRate, double distance, LocalDate date) {
		// Finished Activity constructor
		super(durationInMinutes, averageHeartRate, date);
		this.distance = distance;
	}

	public Rowing(LocalDate date) {
		// Scheduled Activity constructor
		super(date);
		this.distance = 0;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public Rowing copy() {

		if (this.isCompleted()) {
			return new Rowing(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance, this.getDate());
		}
		else {
			return new Rowing(this.getDate());
		}
	}

	@Override
	public double calculateCalories(User user) {
		if (!this.isCompleted()) {
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
