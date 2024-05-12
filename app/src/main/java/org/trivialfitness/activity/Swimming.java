package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;

public class Swimming extends DistanceActivity {

	public Swimming(int distanceKm) {
		super("Swimming", "Distance", true, distanceKm, 12);
	}

	@Override
	public Swimming copy() {
		return new Swimming(getDistanceKm());
	}

}
