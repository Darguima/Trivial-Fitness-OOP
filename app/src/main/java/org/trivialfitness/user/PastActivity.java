package org.trivialfitness.user;

import java.time.LocalDate;

import org.trivialfitness.activity.Activity;

public class PastActivity {

	public Activity activity;

	public int averageHeartRate;

	public int durationInMinutes;

	public LocalDate date;

	public int hour;

	public PastActivity(Activity activity, int averageHeartRate, int durationInMinutes, LocalDate date, int hour) {
		this.activity = activity;
		this.averageHeartRate = averageHeartRate;
		this.durationInMinutes = durationInMinutes;
		this.date = date;
		this.hour = hour;
	}

	public Activity getActivity() {
		return activity.copy();
	}

	public int getAverageHeartRate() {
		return averageHeartRate;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getHour() {
		return hour;
	}

	public PastActivity copy() {
		return new PastActivity(this.activity, this.averageHeartRate, this.durationInMinutes, this.date, this.hour);
	}

}
