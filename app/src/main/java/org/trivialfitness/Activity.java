package org.trivialfitness;

import java.io.Serializable;

public abstract class Activity implements Serializable {

	private int durationInMinutes; // duration in minutes

	private int averageHeartRate; // average heart rate in bpm

	public Activity(int durationInMinutes, int averageHeartRate) {
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = averageHeartRate;

	}

	public Activity() {
		this.durationInMinutes = 0;
		this.averageHeartRate = 0;

	}

	public abstract Activity copy();

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

}
