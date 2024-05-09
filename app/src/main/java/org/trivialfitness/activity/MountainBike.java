package org.trivialfitness.activity;

public class MountainBike extends DistanceAltimetryActivity {

	public MountainBike(int distanceKm, int heightMt) {
		super("Mountain Bike", true, distanceKm, 50, heightMt, 10);
	}

	@Override
	public MountainBike copy() {
		return new MountainBike(getDistanceKm(), getHeightMt());
	}

}
