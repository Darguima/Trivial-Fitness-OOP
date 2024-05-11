package org.trivialfitness.activity;

import org.trivialfitness.activity.activityType.DistanceAltimetryActivity;

public class OutdoorCycling extends DistanceAltimetryActivity {

	public OutdoorCycling(int distanceKm, int heightMt) {
		super("Outdoor Cycling", "Distance and Altimetry", true, distanceKm, 20, heightMt, 5);
	}

	@Override
	public OutdoorCycling copy() {
		return new OutdoorCycling(getDistanceKm(), getHeightMt());
	}

}
// considerando que a bicicleta tem altímetro, acho eu haha