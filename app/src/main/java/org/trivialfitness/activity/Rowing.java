package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceActivity;

public class Rowing extends DistanceActivity {

	public Rowing(int distanceKm) {
		super("Rowing", true, distanceKm, 32);
	}

	@Override
	public Rowing copy() {
		return new Rowing(this.getDistanceKm());
	}

}
