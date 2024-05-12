package org.trivialfitness.trainingPlan;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.trivialfitness.user.User;
import org.trivialfitness.activity.activityType.*;
import org.trivialfitness.state.AppState;
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

	public TrainingPlan(User user, LocalDate startingDate, LocalDate endingDate, int maxActivitiesPerDays,
			int maxDifferentActivities, int activitiesWeeklyFreq, int caloriesGoal, boolean hasHard,
			Class<? extends Activity> activitiesType) {
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.activities = new ArrayList<>();

		List<Activity> availableActivities = AppState.getAvailableActivities()
			.stream()
			.filter(activity -> activity.getClass().getSuperclass().equals(activitiesType))
			.filter(activity -> !activity.isHard() || hasHard)
			.collect(Collectors.toList());

		List<Activity> softActivities = availableActivities.stream()
			.filter(activity -> !activity.isHard())
			.collect(Collectors.toList());

		List<Activity> hardActivities = availableActivities.stream()
			.filter(Activity::isHard)
			.collect(Collectors.toList());

		maxActivitiesPerDays = Math.min(maxActivitiesPerDays, 3);
		maxDifferentActivities = Math.min(maxDifferentActivities, maxActivitiesPerDays * 7 / activitiesWeeklyFreq);

		// How many time a single activity will be repeated in a week
		int softActivitiesWeeklyFreq = activitiesWeeklyFreq;
		int hardActivitiesWeeklyFreq = Math.min(activitiesWeeklyFreq, 3);

		// How many different activities will be selected
		int differentHardActivities = maxDifferentActivities / 3;
		if (hasHard) {
			differentHardActivities = differentHardActivities == 0 ? 1 : differentHardActivities;
			differentHardActivities = Math.min(differentHardActivities, hardActivities.size());
			differentHardActivities = Math.min(differentHardActivities, 7 / hardActivitiesWeeklyFreq);
		}
		else {
			differentHardActivities = 0;
		}

		int differentSoftActivities = Math.min(maxDifferentActivities - differentHardActivities, softActivities.size());

		// The total quantity of activities (even if they are repeated in the week)
		int softActivitiesTotalQnt = differentSoftActivities * softActivitiesWeeklyFreq;
		int hardActivitiesTotalQnt = differentHardActivities * hardActivitiesWeeklyFreq;
		int activitiesTotalQnt = softActivitiesTotalQnt + hardActivitiesTotalQnt;

		int caloryGoalPerActivity = (caloriesGoal / activitiesTotalQnt) + 1;

		// Selecting the activities randomly
		Collections.shuffle(softActivities);
		Collections.shuffle(hardActivities);
		List<Activity> pickedSoftActivities = softActivities.subList(0, differentSoftActivities);
		List<Activity> pickedHardActivities = hardActivities.subList(0, differentHardActivities);

		// Allocating Hard Activities
		DayOfWeek weekDay = DayOfWeek.MONDAY;

		if (hardActivitiesTotalQnt != 0) {
			int restDaysDuration = 7 / (hardActivitiesTotalQnt);
			int remainingRestDays = 7 % (hardActivitiesTotalQnt);

			int activitiesToAllocate = hardActivitiesTotalQnt;
			while (activitiesToAllocate > 0) {
				Activity pickedActivity = pickedHardActivities.get(activitiesToAllocate % pickedHardActivities.size())
					.copy();
				pickedActivity.setActivityAttributesWithCaloryGoal(user, caloryGoalPerActivity);
				TrainingPlanActivity plannedActivity = new TrainingPlanActivity(pickedActivity, weekDay);

				this.activities.add(plannedActivity);

				weekDay = weekDay.plus(restDaysDuration + 1);
				remainingRestDays -= restDaysDuration;

				if (remainingRestDays == 0) {
					restDaysDuration = 0;
				}

				activitiesToAllocate--;
			}
		}

		// Allocating soft Activities
		weekDay = weekDay.plus(3);

		if (softActivitiesTotalQnt != 0) {
			int weekLoopsQnt = softActivitiesTotalQnt / 7 + 1;
			int restDaysDuration = 7 * weekLoopsQnt / (softActivitiesTotalQnt);
			int remainingRestDays = 7 * weekLoopsQnt % (softActivitiesTotalQnt);

			int activitiesToAllocate = softActivitiesTotalQnt;
			while (activitiesToAllocate > 0) {
				Activity pickedActivity = pickedSoftActivities.get(activitiesToAllocate % pickedSoftActivities.size())
					.copy();
				pickedActivity.setActivityAttributesWithCaloryGoal(user, caloryGoalPerActivity);
				TrainingPlanActivity plannedActivity = new TrainingPlanActivity(pickedActivity, weekDay);

				this.activities.add(plannedActivity);

				weekDay = weekDay.plus(restDaysDuration + 1);
				remainingRestDays -= restDaysDuration;

				if (remainingRestDays == 0) {
					restDaysDuration = 0;
				}

				activitiesToAllocate--;
			}
		}

		this.activities.sort(Comparator.comparing(a -> a.getWeekDay()));
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
		long daysDifference = ChronoUnit.DAYS.between(startingDate, endingDate);

		List<PastActivity> pastActivities = new ArrayList<>();

		// For each complete week just add the activities and decrease a week to vars
		LocalDate currentLoopDate = startingDate;
		for (; daysDifference >= 7 && currentLoopDate
			.isBefore(this.endingDate); daysDifference -= 7, currentLoopDate = currentLoopDate.plusDays(7)) {
			for (TrainingPlanActivity activity : activities) {
				// How many days to shift from the weekDay of startingDate
				int weekDaysShift = (activity.getWeekDay().getValue() - currentLoopDate.getDayOfWeek().getValue() + 7)
						% 7;

				// Calculate the release date
				LocalDate releaseDate = currentLoopDate.plusDays(weekDaysShift);

				if (releaseDate.isAfter(this.endingDate)) {
					continue;
				}

				Random random = new Random();
				int randomAverageHearRate = random.nextInt(100, 170);
				int randomDuration = random.nextInt(50, 200);
				int randomHour = random.nextInt(6, 24);

				pastActivities.add(new PastActivity(activity.getActivity(), randomAverageHearRate, randomDuration,
						releaseDate, randomHour));
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

			if (releaseDate.isAfter(this.endingDate)) {
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
