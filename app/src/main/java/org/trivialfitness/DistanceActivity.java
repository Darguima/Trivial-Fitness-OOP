package org.trivialfitness;

public class DistanceActivity extends Activitie {

	private double distance; // distance in kilometers

	public DistanceActivity(int durationInMinutes, int averageHeartRate, double distance, String description) {
		super(durationInMinutes, averageHeartRate, description);
		this.distance = distance;
	}

	public DistanceActivity() {
		super();
		this.distance = 0;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public DistanceActivity copy() {
		return new DistanceActivity(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance,
				this.getDescription());
	}

	@Override
	public double calculateCalories(User user) {

		return (distance * 30 + this.getDurationInMinutes() * 0.5) * user.calculateFitnessMultiplier();
	}

}
