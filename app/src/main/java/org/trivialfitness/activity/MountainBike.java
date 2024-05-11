package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceAltimetryActivity;;

public class MountainBike extends DistanceAltimetryActivity {

	public MountainBike(int distanceKm, int heightMt) {
		super("Mountain Bike", true, distanceKm, 18, heightMt, 10);
	}

	@Override
	public MountainBike copy() {
		return new MountainBike(getDistanceKm(), getHeightMt());
	}

}
