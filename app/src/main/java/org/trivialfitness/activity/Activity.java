package org.trivialfitness.activity;

import java.io.Serializable;

import org.trivialfitness.user.User;

enum ActivityType {

	ALTIMETRY_AND_DISTANCE, DISTANCE, REPETITIONS, REPETITIONS_WITH_WEIGHT

}

public abstract class Activity implements Serializable {

	private ActivityType activityType;

	private String name;

	public Activity(String name, ActivityType activityType) {
		this.name = name;
		this.activityType = activityType;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	/** Returns the type of the activity on a human-readable format */
	public String getActivityTypeString() {
		String type = activityType.toString().replace("_", " ").toLowerCase();
		return type.substring(0, 1).toUpperCase() + type.substring(1);
	}

	public String getActivityName() {
		return this.name;
	}

	public abstract double calculateCalories(User user);

	public abstract Activity copy();

}
