package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;
import org.trivialfitness.user.User;

public class Running extends DistanceActivity {

	public Running(int distanceKm) {
		super("Running", "Distance", true, distanceKm, 20);
	}

	@Override
	public Running copy() {
		return new Running(getDistanceKm());
	}

	public double calculateCalories(User user) {
		return super.calculateCalories(user);
	}

}
