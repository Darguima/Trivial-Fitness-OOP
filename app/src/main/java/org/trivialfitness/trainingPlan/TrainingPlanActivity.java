package org.trivialfitness.trainingPlan;

import java.io.Serializable;
import java.time.DayOfWeek;

import org.trivialfitness.activity.activityType.*;;

public class TrainingPlanActivity implements Serializable {

	public Activity activity;

	public DayOfWeek weekDay;

	public TrainingPlanActivity(Activity activity, DayOfWeek weekDay) {
		this.activity = activity;
		this.weekDay = weekDay;
	}

	public Activity getActivity() {
		return activity.copy();
	}

	public DayOfWeek getWeekDay() {
		return weekDay;
	}

	public TrainingPlanActivity copy() {
		return new TrainingPlanActivity(this.activity, this.weekDay);
	}

}
