package org.trivialfitness;

public class DistanceAndElevationActivity extends Activitie {

	private double distance; // distance in kilometers

	private double elevationGain; // elevation gain in meters

	public DistanceAndElevationActivity(int durationInMinutes, int averageHeartRate, double distance,
			double elevationGain, String description) {
		super(durationInMinutes, averageHeartRate, description);
		this.distance = distance;
		this.elevationGain = elevationGain;
	}

	public DistanceAndElevationActivity() {
		super();
		this.distance = 0;
		this.elevationGain = 0;
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
	public DistanceAndElevationActivity clone() {

		return new DistanceAndElevationActivity(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance,
				this.elevationGain, this.getDescription());
	}

	@Override
	public double calculateCalories(User user) {
		double baseCalories = (distance * 50 + elevationGain * 10);
		double heartRateAdjustment = this.getAverageHeartRate() / 100.0;
		return baseCalories * heartRateAdjustment * user.calculateFitnessMultiplier();
	}

}
