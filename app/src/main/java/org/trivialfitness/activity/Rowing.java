package org.trivialfitness.activity;

import org.trivialfitness.user.User;

public class Rowing extends Activity {

	/** Distance traveled in Km */
	private int distanceKm;

	public Rowing(int distance) {
		super("Rowing", ActivityType.DISTANCE, true);
		this.distanceKm = distance;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	@Override
	public Rowing copy() {
		return new Rowing(this.distanceKm);
	}

	@Override
	public double calculateCalories(User user) {
		return (distanceKm * 32) * user.calculateFitnessMultiplier();
	}

}
