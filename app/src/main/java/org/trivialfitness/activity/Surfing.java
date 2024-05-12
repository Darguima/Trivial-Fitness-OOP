package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;

public class Surfing extends DistanceActivity {

	public Surfing(int distanceKm) {
		super("Surfing", "Distance", true, distanceKm, 12);
	}

	@Override
	public Surfing copy() {
		return new Surfing(getDistanceKm());
	}

}
