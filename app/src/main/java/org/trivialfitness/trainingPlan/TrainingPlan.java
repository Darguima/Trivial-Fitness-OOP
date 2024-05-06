package org.trivialfitness.trainingPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class TrainingPlan implements Serializable {

	private LocalDate startingDate;

	private LocalDate endingDate;

	private List<TrainingPlanActivity> activities;

	public TrainingPlan(LocalDate startingDate, LocalDate endingDate) {
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.activities = new ArrayList<>();
	}

	public TrainingPlan(LocalDate startingDate, LocalDate endingDate, List<TrainingPlanActivity> activities) {
		this.startingDate = startingDate;
		this.endingDate = endingDate;

		setActivities(activities);
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public List<TrainingPlanActivity> getActivities() {
		return activities.stream().map(activity -> activity.copy()).collect(Collectors.toList());
	}

	public void addActivity(TrainingPlanActivity activity) {
		this.activities.add(activity.copy());
	}

	public void setActivities(List<TrainingPlanActivity> activities) {
		this.activities = activities.stream().map(activity -> activity.copy()).collect(Collectors.toList());
	}

	public TrainingPlan copy() {
		TrainingPlan trainingPlan = new TrainingPlan(this.startingDate, this.endingDate);
		trainingPlan.setActivities(this.activities);
		return trainingPlan;
	}

}
