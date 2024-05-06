package org.trivialfitness.user;

public class AmateurUser extends User {

	public AmateurUser(String userId, String name, String address, String email, double weight) {
		super("Amateur User", userId, name, address, email, weight);
	}

	@Override
	public double calculateFitnessMultiplier() {
		double baseMultiplier = 1.0;
		double heartRateEffect = (75 - super.getAverageHeartRate()) / 50.0;
		double weightEffect = this.getWeight() / 100.0;
		return Math.max(baseMultiplier, baseMultiplier + heartRateEffect + weightEffect);
	}

	@Override
	public AmateurUser copy() {
		AmateurUser user = new AmateurUser(this.getUserId(), this.getName(), this.getAddress(), this.getEmail(),
				this.getWeight());
		user.setPastActivities(this.getPastActivities());
		user.setTrainingPlans(this.getTrainingPlans());
		return user;
	}

}
