package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceAltimetryActivity;

public class Climbing extends DistanceAltimetryActivity {

	public Climbing(int distanceKm, int heightMt) {
		super("Climbing", true, distanceKm, 50, heightMt, 0.5);
	}

	@Override
	public Climbing copy() {
		return new Climbing(getDistanceKm(), getHeightMt());
	}

}
