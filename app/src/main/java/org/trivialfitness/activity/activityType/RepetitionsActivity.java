package org.trivialfitness.activity.activityType;

import org.trivialfitness.user.User;

public abstract class RepetitionsActivity extends Activity {

	int repetitions;

	double caloriesPerRepetition;

	/** Constructor to implement others Activity Types */
	public RepetitionsActivity(String activityName, String activityTypeName, boolean hard, int repetitions,
			double caloriesPerRepetition) {
		super(activityName, activityTypeName, hard);
		this.repetitions = repetitions;
		this.caloriesPerRepetition = caloriesPerRepetition;
	}

	/** Constructor to implement final activities */
	public RepetitionsActivity(String activityName, boolean hard, int repetitions, double caloriesPerRepetition) {
		super(activityName, "Repetitions", hard);
		this.repetitions = repetitions;
		this.caloriesPerRepetition = caloriesPerRepetition;
	}

	public int getRepetitions() {
		return repetitions;
	}

	@Override
	public double calculateCalories(User user) {
		return repetitions * caloriesPerRepetition * user.calculateFitnessMultiplier();
	}

	@Override
	public String getActivityAttributesString() {
		return repetitions + " repetitions";
	}

	@Override
	public void setActivityAttributesWithCaloryGoal(User user, int caloriesGoal) {
		this.repetitions = (int) (caloriesGoal / (caloriesPerRepetition * user.calculateFitnessMultiplier())) + 1;
	}

}
