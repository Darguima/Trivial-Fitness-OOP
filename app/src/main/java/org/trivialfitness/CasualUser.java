package org.trivialfitness;

public class CasualUser extends User {

	public CasualUser(String userId, String name, String address, String email, int averageHeartRate) {
		super(userId, name, address, email, averageHeartRate);

	}

	public CasualUser() {
		super();

	}

	@Override
	public double calculateFitnessMultiplier() {
		double baseMultiplier = 0.75;

		double heartRateEffect = (80 - this.getAverageHeartRate()) / 70.0;
		return Math.max(0.75, baseMultiplier + heartRateEffect);
	}

}
