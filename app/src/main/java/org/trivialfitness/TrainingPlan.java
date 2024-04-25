package org.trivialfitness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class TrainingPlan implements Serializable {

	private LocalDate date;

	private List<Activity> activities;

	public TrainingPlan(LocalDate date) {
		this.date = date;
		this.activities = new ArrayList<>();
	}

	public TrainingPlan() {
		this.date = LocalDate.now();
		this.activities = new ArrayList<>();
	}

	public void addActivity(Activity activity) {
		this.activities.add(activity.copy());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Activity> getActivitys() {

		return activities.stream().map(Activity::copy).collect(Collectors.toList());
	}

	public void setActivitys(List<Activity> Activitys) {

		this.activities = Activitys.stream().map(Activity::copy).collect(Collectors.toList());
	}

	public TrainingPlan copy() {

		TrainingPlan trainingPlan = new TrainingPlan(this.date);
		trainingPlan.setActivitys(this.activities);
		return trainingPlan;
	}

}
