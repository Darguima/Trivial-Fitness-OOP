package org.trivialfitness;

// bicicleta de montanha

public class MountainBike extends Activity {

	private double distance; // distance in kilometers

	private double elevationGain; // elevation gain in meters

	public MountainBike(int durationInMinutes, int averageHeartRate, double distance, double elevationGain,
			String description) {
		super(durationInMinutes, averageHeartRate, description);
		this.distance = distance;
		this.elevationGain = elevationGain;
	}

	public MountainBike() {
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
	public MountainBike copy() {

		return new MountainBike(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance,
				this.elevationGain, this.getDescription());
	}

	@Override
	public double calculateCalories(User user) {
		double baseCalories = (distance * 50 + elevationGain * 10);
		double heartRateAdjustment = this.getAverageHeartRate() / 100.0;
		return baseCalories * heartRateAdjustment * user.calculateFitnessMultiplier();
	}

}
