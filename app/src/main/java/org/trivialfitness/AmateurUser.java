package org.trivialfitness;

public class AmateurUser extends User {

	public AmateurUser(String userId, String name, String address, String email, int averageHeartRate, double weight) {
		super(userId, name, address, email, averageHeartRate, weight);
	}

	public AmateurUser() {
		super();
	}

	@Override
	public double calculateFitnessMultiplier() {

		double baseMultiplier = 1.0;
		double heartRateEffect = (75 - super.getAverageHeartRate()) / 50.0;
		double weightEffect = this.getWeight() / 100.0;
		return Math.max(baseMultiplier, baseMultiplier + heartRateEffect + weightEffect);
	}

}
