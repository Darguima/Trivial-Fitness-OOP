package org.trivialfitness;

// flexoes
public class PushUps extends Activitie {

	private int repetitions; // number of repetitions

	public PushUps(int durationInMinutes, int averageHeartRate, int repetitions, String description) {
		super(durationInMinutes, averageHeartRate, description);
		this.repetitions = repetitions;
	}

	public PushUps() {
		super();
		this.repetitions = 0;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	@Override
	public PushUps copy() {
		return new PushUps(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions,
				this.getDescription());
	}

	@Override
	public double calculateCalories(User user) {

		return (repetitions * 0.02) * user.calculateFitnessMultiplier();
	}

}
