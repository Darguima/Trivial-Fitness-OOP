package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public abstract class DistanceActivity extends Activity {

	int distanceKm;

	double caloriesPerDistanceKm;

	/** Constructor to implement others Activity Types */
	public DistanceActivity(String activityName, String activityTypeName, boolean hard, int distanceKm,
			double caloriesPerDistanceKm) {
		super(activityName, activityTypeName, hard);
		this.distanceKm = distanceKm;
		this.caloriesPerDistanceKm = caloriesPerDistanceKm;
	}

	/** Constructor to implement final activities */
	public DistanceActivity(String activityName, boolean hard, int distanceKm, double caloriesPerDistanceKm) {
		super(activityName, "Distance", hard);
		this.distanceKm = distanceKm;
		this.caloriesPerDistanceKm = caloriesPerDistanceKm;
	}

	public int getDistanceKm() {
		return distanceKm;
	}

	@Override
	public double calculateCalories(User user) {
		return (distanceKm * caloriesPerDistanceKm) * user.calculateFitnessMultiplier();
	}

	@Override
	public String getActivityAttributesString() {
		return distanceKm + " Km of distance";
	}

}
