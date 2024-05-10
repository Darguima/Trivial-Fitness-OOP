package org.trivialfitness.activity.activityType;

import org.trivialfitness.user.User;

public abstract class WeightRepetitionsActivity extends RepetitionsActivity {

	int weightKg;

	double caloriesPerWeightKg;

	/** Constructor to implement others Activity Types */
	public WeightRepetitionsActivity(String activityName, String activityTypeName, boolean hard, int repetitions,
			double caloriesPerRepetition, int weightKg, double caloriesPerWeightKg) {
		super(activityName, activityTypeName, hard, repetitions, caloriesPerRepetition);
		this.weightKg = weightKg;
		this.caloriesPerWeightKg = caloriesPerWeightKg;
	}

	/** Constructor to implement final activities */
	public WeightRepetitionsActivity(String activityName, boolean hard, int repetitions, double caloriesPerRepetition,
			int weightKg, double caloriesPerWeightKg) {
		super(activityName, "Repetitions with Weight", hard, repetitions, caloriesPerRepetition);
		this.weightKg = weightKg;
		this.caloriesPerWeightKg = caloriesPerWeightKg;
	}

	public int getWeightKg() {
		return weightKg;
	}

	@Override
	public double calculateCalories(User user) {
		double prevCalories = super.calculateCalories(user);
		return (prevCalories + (weightKg * caloriesPerWeightKg)) * user.calculateFitnessMultiplier();
	}

	@Override
	public String getActivityAttributesString() {
		return super.getActivityAttributesString() + " with " + weightKg + " Kg";
	}

}
