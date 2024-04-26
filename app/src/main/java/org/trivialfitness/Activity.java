package org.trivialfitness;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Activity implements Serializable {

	private int durationInMinutes; // duration in minutes

	private int averageHeartRate; // average heart rate in bpm

	private LocalDate date; // date of the activity

	public Activity(int durationInMinutes, int averageHeartRate, LocalDate date) {
		// Finished Activity constructor
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = averageHeartRate;
		this.date = date;

	}

	public Activity(LocalDate date) {
		// Scheduled Activity constructor
		this.durationInMinutes = 0;
		this.averageHeartRate = 0;
		this.date = date;

	}

	public abstract Activity copy();

	public abstract double calculateCalories(User user);

	public abstract void scheduledToCompleted();

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

	public void updateActivity(int durationInMinutes, int averageHeartRate) {
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = averageHeartRate;
	}

	public LocalDate getDate() {
		return date;
	}

	public boolean isCompleted() {
		// returns true if the activity is completed.

		return !LocalDate.now().isBefore(this.date);
	}

}
