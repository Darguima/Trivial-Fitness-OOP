package org.trivialfitness;

// esta atividade representa canoagem
public class Rowing extends Activity {

	private double distance; // distance in kilometers

	public Rowing(int durationInMinutes, int averageHeartRate, double distance) {
		super(durationInMinutes, averageHeartRate);
		this.distance = distance;
	}

	public Rowing() {
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
	public Rowing copy() {
		return new Rowing(this.getDurationInMinutes(), this.getAverageHeartRate(), this.distance);
	}

	@Override
	public double calculateCalories(User user) {

		return (distance * 30 + this.getDurationInMinutes() * 0.5) * user.calculateFitnessMultiplier();
	}

}
