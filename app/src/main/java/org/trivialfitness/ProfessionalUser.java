package org.trivialfitness;

public class ProfessionalUser extends User {

	public ProfessionalUser(String userId, String name, String address, String email, int averageHeartRate,
			double weight) {
		super(userId, name, address, email, averageHeartRate, weight);
	}

	public ProfessionalUser() {
		super();
	}

	@Override
	public double calculateFitnessMultiplier() {
		double baseMultiplier = 1.2;
		double weightEffect = this.getWeight() / 100.0;
		double heartRateEffect = (60 - this.getAverageHeartRate()) / 50.0;

		return Math.max(baseMultiplier, baseMultiplier + heartRateEffect + weightEffect);
	}

}