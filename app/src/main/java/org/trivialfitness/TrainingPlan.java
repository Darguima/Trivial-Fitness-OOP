package org.trivialfitness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class TrainingPlan implements Serializable {

	private LocalDate startingDate;
	private LocalDate endingDate;

	private List<Activity> activities;

	public TrainingPlan(LocalDate startingDate, LocalDate endingDate) {
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.activities = new ArrayList<>();
	}

	public TrainingPlan() {
		this.startingDate = LocalDate.now();
		this.activities = new ArrayList<>();
	}

	public void addActivity(Activity activity, LocalDate dateNow) {
		this.activities.add(activity.copy(dateNow));
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setStartingDate(LocalDate date) {
		this.startingDate = date;
	}

	public void setEndingDate(LocalDate date) {
		this.endingDate = date;
	}

	public List<Activity> getActivities(LocalDate dateNow) {
		return activities.stream().map(activity -> activity.copy(dateNow)).collect(Collectors.toList());
	}

	public void setActivities(List<Activity> activities, LocalDate dateNow) {

		this.activities = activities.stream().map(activity -> activity.copy(dateNow)).collect(Collectors.toList());
	}

	public TrainingPlan copy(LocalDate dateNow) {

		TrainingPlan trainingPlan = new TrainingPlan(this.startingDate, this.endingDate);
		trainingPlan.setActivities(this.activities, dateNow);
		return trainingPlan;
	}

	/**
	 * Add training plan activities to the user's activities, using the function dayIsBeforeCurrentDay
	*/
	public void addTrainingPlanActivityToUser(User user, LocalDate dateBefore, LocalDate nowDate) {
		for (Activity activity : this.activities) {
			// if the activity has a null date we will check if it is before the current
			// date
			if (activity.getDate() == null && activity.dayIsBefore(dateBefore, nowDate)) {
				user.addTrainingPlanActivityToUser(user, activity, nowDate);
			}
		}
	}

}
