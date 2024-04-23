package org.trivialfitness;

public class RepetitionActivity extends Activitie {

	private int repetitions; // number of repetitions

	public RepetitionActivity(int durationInMinutes, int averageHeartRate, int repetitions, String description) {
		super(durationInMinutes, averageHeartRate, description);
		this.repetitions = repetitions;
	}

	public RepetitionActivity() {
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
	public RepetitionActivity copy() {
		return new RepetitionActivity(this.getDurationInMinutes(), this.getAverageHeartRate(), this.repetitions,
				this.getDescription());
	}

	@Override
	public double calculateCalories(User user) {

		return (repetitions * 0.02) * user.calculateFitnessMultiplier();
	}

}
