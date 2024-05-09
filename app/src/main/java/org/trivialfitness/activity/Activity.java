package org.trivialfitness.activity;

import java.io.Serializable;

import org.trivialfitness.user.User;

public abstract class Activity implements Serializable {

	private String activityName;

	private String activityTypeName;

	private boolean hard;

	public Activity(String activityName, String activityTypeName, boolean hard) {
		this.activityName = activityName;
		this.activityTypeName = activityTypeName;
		this.hard = hard;
	}

	public String getActivityName() {
		return activityName;
	}

	public String getActivityTypeName() {
		return activityTypeName;
	}

	public boolean isHard() {
		return hard;
	}

	public abstract double calculateCalories(User user);

	public abstract Activity copy();

}
