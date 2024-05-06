package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public class MountainBike extends Activity {

	/** Distance traveled in Km */
	private double distanceKm;

	/** Elevation gains in meters */
	private double elevationGain;

	public MountainBike(double distance, double elevationGain) {
		super("Mountain Bike", ActivityType.ALTIMETRY_AND_DISTANCE);
		this.distanceKm = distance;
		this.elevationGain = elevationGain;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public double getElevationGain() {
		return elevationGain;
	}

	@Override
	public MountainBike copy() {
		return new MountainBike(this.distanceKm, this.elevationGain);
	}

	@Override
	public double calculateCalories(User user) {
		double baseCalories = (distanceKm * 50 + elevationGain * 10);
		double heartRateAdjustment = Math.max(user.getAverageHeartRate() / 100.0, 0.05);

		return baseCalories * heartRateAdjustment * user.calculateFitnessMultiplier();
	}

}
