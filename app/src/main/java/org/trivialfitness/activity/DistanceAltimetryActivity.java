package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public abstract class DistanceAltimetryActivity extends DistanceActivity {

	int heightMt;

	double caloriesPerHeightMt;

	/** Constructor to implement others Activity Types */
	public DistanceAltimetryActivity(String activityName, String activityTypeName, boolean hard, int distanceKm,
			double caloriesPerDistanceKm, int heightMt, double caloriesPerHeightMt) {
		super(activityName, activityTypeName, hard, distanceKm, caloriesPerDistanceKm);
		this.heightMt = heightMt;
		this.caloriesPerHeightMt = caloriesPerHeightMt;
	}

	/** Constructor to implement final activities */
	public DistanceAltimetryActivity(String activityName, boolean hard, int distanceKm, double caloriesPerDistanceKm,
			int heightMt, double caloriesPerHeightMt) {
		super(activityName, "Distance and Altimetry", hard, distanceKm, caloriesPerDistanceKm);
		this.heightMt = heightMt;
		this.caloriesPerHeightMt = caloriesPerHeightMt;
	}

	public int getHeightMt() {
		return heightMt;
	}

	@Override
	public double calculateCalories(User user) {
		double prevCalories = super.calculateCalories(user);
		return prevCalories + (heightMt * caloriesPerHeightMt * user.calculateFitnessMultiplier());
	}

	@Override
	public String getActivityAttributesString() {
		return super.getActivityAttributesString() + " and " + heightMt + " Mt of elevation gain";
	}

	@Override
	public void setActivityAttributesWithCaloryGoal(User user, int caloriesGoal) {
		this.distanceKm = (int) (caloriesGoal * 0.9 / (caloriesPerDistanceKm * user.calculateFitnessMultiplier())) + 1;
		this.heightMt = (int) ((caloriesGoal * 0.1) / (caloriesPerHeightMt * user.calculateFitnessMultiplier())) + 1;
	}

}
