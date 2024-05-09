package org.trivialfitness.activity;

public class Rowing extends DistanceActivity {

	public Rowing(int distanceKm) {
		super("Rowing", true, distanceKm, 32);
		this.distanceKm = distanceKm;
	}

	@Override
	public Rowing copy() {
		return new Rowing(this.distanceKm);
	}

}
