package org.trivialfitness;

import java.time.DayOfWeek;
import java.time.LocalDate;

// bicicleta de montanha

public class MountainBike extends Activity {

	private double distance; // distance in kilometers

	private double elevationGain; // elevation gain in meters

	/** Create a finished Mountain Bike Activity */
	public MountainBike(int durationInMinutes, LocalDate realizationDate, int averageHeartRate, double distance,
			double elevationGain) {
		super(durationInMinutes, realizationDate, averageHeartRate);
		this.distance = distance;
		this.elevationGain = elevationGain;
	}

	/** Create an individual scheduled Mountain Bike Activity */
	public MountainBike(int durationInMinutes, LocalDate realizationDate) {
		super(durationInMinutes, realizationDate);
		this.distance = -1;
		this.elevationGain = -1;
	}

	/** Create a Training Plan weekly scheduled Mountain Bike Activity */
	public MountainBike(DayOfWeek weekDay, double distance, double elevationGain) {
		super(weekDay);
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
	public MountainBike copy(LocalDate dateNow) {

		if (this.isCompleted(dateNow)) {
			return new MountainBike(this.getDurationInMinutes(), this.getDate(), this.getAverageHeartRate(),
					this.distance, this.elevationGain);
		}
		else {
			if (this.getDate() != null) {
				return new MountainBike(this.getDurationInMinutes(), this.getDate());
			}
			else {
				return new MountainBike(this.getDay(), this.getDistance(), this.getElevationGain());
			}
		}
	}

	@Override
	public double calculateCalories(User user, LocalDate dateNow) {

		if (!this.isCompleted(dateNow)) {
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
