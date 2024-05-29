package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceAltimetryActivity;

public class Climbing extends DistanceAltimetryActivity {

	public Climbing(int distanceKm, int heightMt) {
		super("Climbing", false, distanceKm, 45, heightMt, 2);
	}

	@Override
	public Climbing copy() {
		return new Climbing(getDistanceKm(), getHeightMt());
	}

}
