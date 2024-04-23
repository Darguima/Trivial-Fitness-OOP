package org.trivialfitness;

public class WeightedRepetitionActivity extends Activitie {

	private int repetitions; // number of repetitions

	private double weight; // weight in kilograms

	public WeightedRepetitionActivity(int durationInMinutes, int averageHeartRate, int repetitions, double weight,
			String description) {
		super(durationInMinutes, averageHeartRate, description);
		this.repetitions = repetitions;
		this.weight = weight;
	}

	public WeightedRepetitionActivity() {
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
	public WeightedRepetitionActivity clone() {
		return new WeightedRepetitionActivity(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions,
				this.weight, this.getDescription());
	}

	@Override
	public double calculateCalories(User user) {

		return (repetitions * weight * 0.03) * user.calculateFitnessMultiplier();
	}
}