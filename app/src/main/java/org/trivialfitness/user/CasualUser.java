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

	@Override
	public CasualUser copy() {
		CasualUser user = new CasualUser(this.getUserId(), this.getName(), this.getAddress(), this.getEmail(),
				this.getWeight());
		user.setPastActivities(this.getPastActivities());
		user.setTrainingPlans(this.getTrainingPlans());
		return user;
	}

}
