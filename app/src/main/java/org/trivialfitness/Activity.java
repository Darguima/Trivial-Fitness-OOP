package org.trivialfitness;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public abstract class Activity implements Serializable {

	private int durationInMinutes;

	private int averageHeartRate;

	private LocalDate date; // date of the realization of the activity

	private DayOfWeek weekDay; // weekday (1-7, 1 = Monday, 7 = Sunday)

	/** Create a finished Activity */
	public Activity(int durationInMinutes, LocalDate realizationDate, int averageHeartRate) {
		this.durationInMinutes = durationInMinutes;
		this.averageHeartRate = averageHeartRate;
		this.date = realizationDate;
		this.weekDay = realizationDate.getDayOfWeek();
	}

	/** Create an individual scheduled Activity */
	public Activity(int durationInMinutes, LocalDate realizationDate) {
		this.durationInMinutes = durationInMinutes;
		this.date = realizationDate;
		this.weekDay = realizationDate.getDayOfWeek();

		// Activity is not completed yet
		this.averageHeartRate = -1;
	}

	/** Create a Training Plan weekly scheduled Activity */
	public Activity(DayOfWeek weekDay) {
		// Training Plan Activity constructor
		this.weekDay = weekDay;

		// Activity is not completed yet
		this.durationInMinutes = -1;
		this.averageHeartRate = -1;
		this.date = null;
	}

	public abstract Activity copy(LocalDate comparisonDate);

	public abstract double calculateCalories(User user, LocalDate comparisonDate);

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
		return this.date;
	}

	public DayOfWeek getDay() {
		return this.weekDay;
	}

	public void setDate(LocalDate date) {
		this.date = date;
		this.weekDay = date.getDayOfWeek();
	}

	public boolean isCompleted(LocalDate comparisonDate) {
		// returns true if the activity is completed.

		return this.date != null && this.date.isBefore(comparisonDate);
	}

	public boolean dayIsBefore(LocalDate dateBefore, LocalDate dateNow) {
		// Find the next occurrence of the day of the week of the activity after
		// 'dateBefore'
		LocalDate nextThisDay = nextOccurrenceOfWeekDay(this.getDay(), dateBefore);

		// Verify if the next occurrence of the day of the week of the activity is
		// before
		// or exactly on 'dateNow'
		if (!nextThisDay.isAfter(dateNow)) {
			// If so, the activity is completed
			// Set the date of the activity to the next occurrence of the day of the week
			// of the activity after 'dateNow'
			this.setDate(nextThisDay);
			return true;
		}
		return false;
	}

	/**
	 * Helper method to find the next occurrence of a day of the week after a given date
	 */
	private LocalDate nextOccurrenceOfWeekDay(DayOfWeek weekDay, LocalDate initialDate) {
		int daysToAdd = (weekDay.getValue() - initialDate.getDayOfWeek().getValue() + 7) % 7;
		daysToAdd = daysToAdd == 0 ? 7 : daysToAdd;
		return initialDate.plusDays(daysToAdd);
	}

}
