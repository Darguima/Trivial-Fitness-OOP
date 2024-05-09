package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public abstract class RepetitionsActivity extends Activity {

	int repetitions;

	double caloriesPerRepetition;

	/** Constructor to implement others Activity Types */
	public RepetitionsActivity(String activityName, String activityTypeName, boolean hard, int repetitions,
			double caloriesRepetition) {
		super(activityName, activityTypeName, hard);
		this.repetitions = repetitions;
		this.caloriesPerRepetition = caloriesRepetition;
	}

	/** Constructor to implement final activities */
	public RepetitionsActivity(String activityName, boolean hard, int repetitions, double caloriesRepetition) {
		super(activityName, "Repetitions", hard);
		this.repetitions = repetitions;
		this.caloriesPerRepetition = caloriesRepetition;
	}

	public int getRepetitions() {
		return repetitions;
	}

	@Override
	public double calculateCalories(User user) {
		return (repetitions * caloriesPerRepetition) * user.calculateFitnessMultiplier();
	}

}
