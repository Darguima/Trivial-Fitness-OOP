package org.trivialfitness.trainingPlan;

import java.time.DayOfWeek;

import org.trivialfitness.activity.Activity;

public class TrainingPlanActivity {

	public Activity activity;

	public DayOfWeek weekDay;

	public TrainingPlanActivity(Activity activity, DayOfWeek weekDay) {
		this.activity = activity;
		this.weekDay = weekDay;
	}

	public TrainingPlanActivity copy() {
		return new TrainingPlanActivity(this.activity, this.weekDay);
	}

}
