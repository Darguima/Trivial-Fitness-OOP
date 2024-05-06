package org.trivialfitness.user;

public class ProfessionalUser extends User {

	public ProfessionalUser(String userId, String name, String address, String email, double weight) {
		super("Professional User", userId, name, address, email, weight);
	}

	@Override
	public double calculateFitnessMultiplier() {
		double baseMultiplier = 1.2;
		double weightEffect = this.getWeight() / 100.0;
		double heartRateEffect = (60 - this.getAverageHeartRate()) / 50.0;

		return Math.max(baseMultiplier, baseMultiplier + heartRateEffect + weightEffect);
	}

	@Override
	public ProfessionalUser copy() {
		ProfessionalUser user = new ProfessionalUser(this.getUserId(), this.getName(), this.getAddress(),
				this.getEmail(), this.getWeight());
		user.setPastActivities(this.getPastActivities());
		user.setTrainingPlans(this.getTrainingPlans());
		return user;
	}

}
