package org.trivialfitness.trainingPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.trivialfitness.user.PastActivity;

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

	public List<PastActivity> getActivitiesBetweenDates(LocalDate startingDate, LocalDate endingDate) {
		int daysDifference = (int) startingDate.until(endingDate).getDays();

		List<PastActivity> pastActivities = new ArrayList<>();

		// For each complete week just add the activities and decrease a week to vars
		LocalDate currentLoopDate = startingDate;
		for (; daysDifference >= 7; daysDifference -= 7, currentLoopDate = currentLoopDate.plusDays(7)) {
			for (TrainingPlanActivity activity : activities) {
				// How many days to shift from the weekDay of startingDate
				int weekDaysShift = (activity.getWeekDay().getValue() - currentLoopDate.getDayOfWeek().getValue() + 7)
						% 7;

				// Calculate the release date
				LocalDate releaseDate = currentLoopDate.plusDays(weekDaysShift);

				if (releaseDate.isAfter(endingDate)) {
					continue;
				}

				pastActivities.add(new PastActivity(activity.getActivity(), 0, 0, releaseDate, 0));
			}
		}

		// Add incomplete final week
		int maxWeekDayShift = (endingDate.getDayOfWeek().getValue() - currentLoopDate.getDayOfWeek().getValue() + 7)
				% 7;
		for (TrainingPlanActivity activity : activities) {
			// How many days to shift from the weekDay of startingDate
			int weekDaysShift = (activity.getWeekDay().getValue() - currentLoopDate.getDayOfWeek().getValue() + 7) % 7;

			if (weekDaysShift >= maxWeekDayShift) {
				continue;
			}

			LocalDate releaseDate = currentLoopDate.plusDays(weekDaysShift);

			if (releaseDate.isAfter(endingDate)) {
				continue;
			}

			pastActivities.add(new PastActivity(activity.getActivity(), 0, 0, releaseDate, 0));
		}

		return pastActivities;
	}

	public TrainingPlan copy() {
		TrainingPlan trainingPlan = new TrainingPlan(this.startingDate, this.endingDate);
		trainingPlan.setActivities(this.activities);
		return trainingPlan;
	}

}
