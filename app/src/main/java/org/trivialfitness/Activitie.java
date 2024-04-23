package org.trivialfitness;

import java.io.Serializable;

public abstract class Activitie implements Serializable {

	private int durationInMinutes; // duration in minutes

	private int averageHeartRate; // average heart rate in bpm

	private String description; // description of the activity

	public Activitie(int durationInMinutes, int averageHeartRate, String description) {
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = averageHeartRate;
		this.description = description;
	}

	public Activitie() {
		this.durationInMinutes = 0;
		this.averageHeartRate = 0;
		this.description = "";
	}

	public abstract Activitie copy();

	public abstract double calculateCalories(User user);

	public int getDurationInMinutes() {
		return this.durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public int getAverageHeartRate() {
		return this.averageHeartRate;
	}

	public void setAverageHeartRate(int averageHeartRate) {
		this.averageHeartRate = averageHeartRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
