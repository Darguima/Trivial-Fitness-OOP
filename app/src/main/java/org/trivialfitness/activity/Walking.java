package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;

public class Walking extends DistanceActivity {

	public Walking(int distanceKm) {
		super("Walking", "Distance", false, distanceKm, 8);
	}

	@Override
	public Walking copy() {
		return new Walking(getDistanceKm());
	}

}
