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

	public void addActivity(Activity activity, LocalDate datenow) {
		this.activities.add(activity.copy(datenow));
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Activity> getActivities(LocalDate datenow) {
		return activities.stream().map(activity -> activity.copy(datenow)).collect(Collectors.toList());
	}

	public void setActivitys(List<Activity> activities, LocalDate datenow) {

		this.activities = activities.stream().map(activity -> activity.copy(datenow)).collect(Collectors.toList());
	}

	public TrainingPlan copy(LocalDate datenow) {

		TrainingPlan trainingPlan = new TrainingPlan(this.date);
		trainingPlan.setActivitys(this.activities, datenow);
		return trainingPlan;
	}

	// functiion that ads training plan activities to the user's activities, using the
	// function dayIsBeforeCurrentDay
	public void addTrainingPlanActivityToUser(User user, LocalDate datebefore, LocalDate nowDate) {
		for (Activity activity : this.activities) {
			if (activity.dayIsBefore(datebefore, nowDate)) {
				user.addActivity(activity, nowDate);
			}
		}
	}

}
