package org.trivialfitness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class User {

	private String userId;

	private String name;

	private String address;

	private String email;

	private int averageHeartRate;

	private double weight;

	private List<Activity> activities;

	private List<TrainingPlan> trainingPlans;

	public User(String userId, String name, String address, String email, int averageHeartRate, double weight) {
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.averageHeartRate = averageHeartRate;
		this.activities = new ArrayList<>();
		this.trainingPlans = new ArrayList<>();
		this.weight = weight;
	}

	public abstract double calculateFitnessMultiplier();

	public void addActivity(Activity activity) {
		activities.add(activity.copy());
	}

	// Getters
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

	public int getAverageHeartRate() {
		return averageHeartRate;
	}

	public double getWeight() {
		return weight;
	}

	public List<Activity> getActivities() {
		// return copy of Activitys using streams, and using clone method
		return activities.stream().map(Activity::copy).collect(Collectors.toList());

	}

	public List<TrainingPlan> getTrainingPlans() {
		// return copy of trainingPlans using streams, and using clone method
		return trainingPlans.stream().map(TrainingPlan::copy).collect(Collectors.toList());
	}

	public void seTrainingPlans(List<TrainingPlan> trainingPlans) {
		// return copy of trainingPlans using streams, and using clone method
		this.trainingPlans = trainingPlans.stream().map(TrainingPlan::copy).collect(Collectors.toList());
	}

	public void addTrainingPlan(TrainingPlan trainingPlan) {
		trainingPlans.add(trainingPlan.copy());
	}

}
