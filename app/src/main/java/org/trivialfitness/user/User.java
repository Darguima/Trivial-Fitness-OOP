package org.trivialfitness.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.trivialfitness.trainingPlan.TrainingPlan;

public abstract class User {

	private String userType;

	private String userId;

	private String name;

	private String address;

	private String email;

	private double weight;

	private int averageHeartRateSum;

	private List<PastActivity> pastActivities;

	private List<TrainingPlan> trainingPlans;

	public User(String userType, String userId, String name, String address, String email, double weight) {
		this.userType = userType;
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.weight = weight;

		this.averageHeartRateSum = 0;

		this.pastActivities = new ArrayList<>();
		this.trainingPlans = new ArrayList<>();
	}

	public String getUserType() {
		return userType;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public double getWeight() {
		return weight;
	}

	public int getAverageHeartRate() {
		return pastActivities.isEmpty() ? 0 : averageHeartRateSum / pastActivities.size();
	}

	public abstract double calculateFitnessMultiplier();

	public List<PastActivity> getPastActivities() {
		return pastActivities.stream().map(pastActivity -> pastActivity.copy()).collect(Collectors.toList());
	}

	public void addPastActivity(PastActivity pastActivity) {
		pastActivities.add(pastActivity.copy());
	}

	public void setPastActivities(List<PastActivity> pastActivities) {
		this.pastActivities = pastActivities.stream()
			.map(pastActivity -> pastActivity.copy())
			.collect(Collectors.toList());
	}

	public List<TrainingPlan> getTrainingPlans() {
		return trainingPlans.stream().map(trainingPlans -> trainingPlans.copy()).collect(Collectors.toList());
	}

	public void addTrainingPlan(TrainingPlan trainingPlan) {
		trainingPlans.add(trainingPlan.copy());
	}

	public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
		this.trainingPlans = trainingPlans.stream()
			.map(trainingPlan -> trainingPlan.copy())
			.collect(Collectors.toList());
	}

	public abstract User copy();
		

}
