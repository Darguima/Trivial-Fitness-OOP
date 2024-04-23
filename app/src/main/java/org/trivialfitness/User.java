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

	private List<Activitie> activities;

	private List<TrainingPlan> trainingPlans;

	public User(String userId, String name, String address, String email, int averageHeartRate) {
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.averageHeartRate = averageHeartRate;
		this.activities = new ArrayList<>();
		this.trainingPlans = new ArrayList<>();
	}

	public User() {
		this.userId = "";
		this.name = "";
		this.address = "";
		this.email = "";
		this.averageHeartRate = 0;
		this.activities = new ArrayList<>();
		this.trainingPlans = new ArrayList<>();
	}

	public abstract double calculateFitnessMultiplier();

	public void addActivity(Activitie activity) {
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

	public List<Activitie> getActivities() {
		// return copy of activities using streams, and using clone method
		return activities.stream().map(Activitie::copy).collect(Collectors.toList());

	}

	public List<TrainingPlan> getTrainingPlans() {
		// return copy of trainingPlans using streams, and using clone method
		return trainingPlans.stream().map(TrainingPlan::copy).collect(Collectors.toList());
	}

	public void seTrainingPlans(List<TrainingPlan> trainingPlans) {
		// return copy of trainingPlans using streams, and using clone method
		this.trainingPlans = trainingPlans.stream().map(TrainingPlan::copy).collect(Collectors.toList());
	}

}
