package org.trivialfitness.activity.activityType;

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
		return (heightMt * caloriesPerHeightMt) * user.calculateFitnessMultiplier() + prevCalories;
	}

	@Override
	public String getActivityAttributesString() {
		return super.getActivityAttributesString() + " and " + heightMt + " Mt of elevation gain";
	}

}
