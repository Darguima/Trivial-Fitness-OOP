package org.trivialfitness;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public abstract class Activity implements Serializable {

	private int durationInMinutes; // duration in minutes

	private int averageHeartRate; // average heart rate in bpm

	private LocalDate date; // date of the activity

	private DayOfWeek day; // weekday (1-7, 1 = Monday, 7 = Sunday)

	public Activity(int durationInMinutes, int averageHeartRate, LocalDate date) {
		// Finished Activity constructor
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = averageHeartRate;
		this.date = date;
		this.day = date.getDayOfWeek();

	}

	public Activity(LocalDate date, int durationInMinutes) {
		// Scheduled Activity constructor, user can set the duration
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = 0;
		this.date = date;
		this.day = date.getDayOfWeek();

	}

	public Activity(DayOfWeek day) {
		// Training Plan Activity constructor
		this.durationInMinutes = 60; // random value
		this.averageHeartRate = 0; // 0 because it is a training plan activity
		this.date = null; // date is null because it is a training plan activity and it
							// can be made in any date
		this.day = day;
	}

	public abstract Activity copy(LocalDate datenow);

	public abstract double calculateCalories(User user, LocalDate datenow);

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

	public DayOfWeek getDay() {
		return day;
	}

	public void setDate(LocalDate date) {
		this.date = date;
		this.day = date.getDayOfWeek();
	}

	public boolean isCompleted(LocalDate datenow) {
		// returns true if the activity is completed.

		return this.date != null && this.date.isBefore(datenow);
	}

	public boolean dayIsBefore(LocalDate datebefore, LocalDate datenow) {
		// Find the next occurrence of the day of the week of the activity after
		// 'datebefore'
		LocalDate nextThisDay = nextOccurrenceOfDay(this.getDay(), datebefore);

		// Verify if the next occurrence of the day of the week of the activity is before
		// or exactly on 'datenow'
		if (!nextThisDay.isAfter(datenow)) {
			// If so, the activity is completed
			// Set the date of the activity to the next occurrence of the day of the week
			// of the activity after 'datenow'
			this.setDate(nextThisDay);
			return true;
		}
		return false;
	}

	// Helper method to find the next occurrence of a day of the week after a given date
	private LocalDate nextOccurrenceOfDay(DayOfWeek day, LocalDate after) {
		int daysToAdd = (day.getValue() - after.getDayOfWeek().getValue() + 7) % 7;
		daysToAdd = daysToAdd == 0 ? 7 : daysToAdd;
		return after.plusDays(daysToAdd);
	}

}
