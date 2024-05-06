package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

// bicicleta de montanha

public class MountainBike extends Activity {

	private double distance; // distance in kilometers

	private double elevationGain; // elevation gain in meters

	public MountainBike(int durationInMinutes, int averageHeartRate, double distance, double elevationGain,
			LocalDate date) {
		// Finished Activity constructor
		super(durationInMinutes, averageHeartRate, date);
		this.distance = distance;
		this.elevationGain = elevationGain;
	}

	public MountainBike(LocalDate date, int durationInMinutes) {
		// Scheduled Activity constructor
		super(date, durationInMinutes);
		this.distance = 0;
		this.elevationGain = 0;
	}

	public MountainBike(DayOfWeek day, double distance, double elevationGain) {
		// Training Plan Activity constructor
		super(day);
		this.distance = distance; // random value
		this.elevationGain = elevationGain; // random value
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getElevationGain() {
		return elevationGain;
	}

	public void setElevationGain(double elevationGain) {
		this.elevationGain = elevationGain;
	}

	@Override
	public MountainBike copy(LocalDate datenow) {

		if (this.isCompleted(datenow)) {
			return new MountainBike(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance,
					this.elevationGain, this.getDate());
		}
		else {
			if (this.getDate() != null) {
				return new MountainBike(this.getDate(), this.getDurationInMinutes());
			}
			else {
				return new MountainBike(this.getDay(), this.getDistance(), this.getElevationGain());
			}
		}
	}

	@Override
	public double calculateCalories(User user, LocalDate datenow) {

		if (!this.isCompleted(datenow)) {
			return 0;
		}

		double baseCalories = (distance * 50 + elevationGain * 10);
		double heartRateAdjustment = this.getAverageHeartRate() / 100.0;
		return baseCalories * heartRateAdjustment * user.calculateFitnessMultiplier();
	}

	@Override
	public void scheduledToCompleted() {
		this.updateActivity(10, 100);
		this.setDistance(20);
		this.setElevationGain(200);
		// Random values, just to test the method
	}

}
