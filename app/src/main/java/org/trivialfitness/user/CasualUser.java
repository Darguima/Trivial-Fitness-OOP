package org.trivialfitness.user;

public class CasualUser extends User {

	public CasualUser(String userId, String name, String address, String email, double weight) {
		super("Casual User", userId, name, address, email, weight);
	}

	@Override
	public double calculateFitnessMultiplier() {
		double baseMultiplier = 0.75;
		double heartRateEffect = (80 - this.getAverageHeartRate()) / 70.0;
		double weightEffect = this.getWeight() / 100.0;

		return Math.max(baseMultiplier, baseMultiplier + heartRateEffect + weightEffect);
	}

}