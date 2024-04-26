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

	private List<Activity> pastActivities;

	private List<Activity> scheduledActivities;

	private List<TrainingPlan> trainingPlans;

	public User(String userId, String name, String address, String email, int averageHeartRate, double weight) {
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.averageHeartRate = averageHeartRate;
		this.pastActivities = new ArrayList<>();
		this.scheduledActivities = new ArrayList<>();
		this.trainingPlans = new ArrayList<>();
		this.weight = weight;
	}

	public abstract double calculateFitnessMultiplier();

	public void addActivity(Activity activity) {
		if (activity.isCompleted()) {
			pastActivities.add(activity.copy());
		}
		else {
			scheduledActivities.add(activity.copy());
		}
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

	public List<Activity> getPastActivities() {
		// return copy of Past Activities using streams, and using clone method
		return pastActivities.stream().map(Activity::copy).collect(Collectors.toList());

	}

	public void getScheduledActivities(List<Activity> Scheduledactivities) {
		// return copy of Scheduled Activities using streams, and using clone method
		this.scheduledActivities = scheduledActivities.stream().map(Activity::copy).collect(Collectors.toList());
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
