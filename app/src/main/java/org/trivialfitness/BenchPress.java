package org.trivialfitness;

public class BenchPress extends Activity {

	private int repetitions; // number of repetitions

	private double weight; // weight in kilograms

	public BenchPress(int durationInMinutes, int averageHeartRate, int repetitions, double weight) {
		super(durationInMinutes, averageHeartRate);
		this.repetitions = repetitions;
		this.weight = weight;
	}

	public BenchPress() {
		super();
		this.repetitions = 0;
		this.weight = 0;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public BenchPress copy() {
		return new BenchPress(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions, this.weight);
	}

	@Override
	public double calculateCalories(User user) {

		return (repetitions * weight * 0.03) * user.calculateFitnessMultiplier();
	}

}