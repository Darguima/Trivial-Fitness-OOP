package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;
import org.trivialfitness.user.User;

public class Walking extends DistanceActivity {

	public Walking(int distanceKm) {
		super("Walking", "Distance", true, distanceKm, 8);
	}

	@Override
	public Walking copy() {
		return new Walking(getDistanceKm());
	}

	public double calculateCalories(User user) {
		return super.calculateCalories(user);
	}

}
