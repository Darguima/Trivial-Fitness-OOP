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
     
        double caloriesPerRep = 0.11;
		double weightLifted = user.getWeight() * 0.64;
        return repetitions * weightLifted*caloriesPerRep * user.calculateFitnessMultiplier();
    }

}