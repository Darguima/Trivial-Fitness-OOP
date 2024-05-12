package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;

public class Running extends DistanceActivity {

	public Running(int distanceKm) {
		super("Running", "Distance", true, distanceKm, 14);
	}

	@Override
	public Running copy() {
		return new Running(getDistanceKm());
	}

}
