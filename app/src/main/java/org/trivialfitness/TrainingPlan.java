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

	public void addActivity(Activity activity, LocalDate dateNow) {
		this.activities.add(activity.copy(dateNow));
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Activity> getActivities(LocalDate dateNow) {
		return activities.stream().map(activity -> activity.copy(dateNow)).collect(Collectors.toList());
	}

	public void setActivitys(List<Activity> activities, LocalDate dateNow) {

		this.activities = activities.stream().map(activity -> activity.copy(dateNow)).collect(Collectors.toList());
	}

	public TrainingPlan copy(LocalDate dateNow) {

		TrainingPlan trainingPlan = new TrainingPlan(this.date);
		trainingPlan.setActivitys(this.activities, dateNow);
		return trainingPlan;
	}

	// functiion that ads training plan activities to the user's activities, using the
	// function dayIsBeforeCurrentDay
	public void addTrainingPlanActivityToUser(User user, LocalDate datebefore, LocalDate nowDate) {
		for (Activity activity : this.activities) {
			// if the activity has a null date we will check if it is before the current
			// date
			if (activity.getDate() == null && activity.dayIsBefore(datebefore, nowDate)) {
				user.addTrainingPlanActivityToUser(user, activity, nowDate);
			}
		}
	}

}
