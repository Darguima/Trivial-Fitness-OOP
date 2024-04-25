package org.trivialfitness;

public class CasualUser extends User {

	public CasualUser(String userId, String name, String address, String email, int averageHeartRate, double weight) {
		super(userId, name, address, email, averageHeartRate, weight);

	}

	public CasualUser() {
		super();

	}

	@Override
	public double calculateFitnessMultiplier() {
		double baseMultiplier = 0.75;
		double heartRateEffect = (80 - this.getAverageHeartRate()) / 70.0;
		double weightEffect = this.getWeight() / 100.0;

		return Math.max(baseMultiplier, baseMultiplier + heartRateEffect + weightEffect);
	}

}
