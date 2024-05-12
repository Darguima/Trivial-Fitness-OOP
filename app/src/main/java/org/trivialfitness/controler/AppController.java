package org.trivialfitness.controler;

import org.trivialfitness.user.AmateurUser;
import org.trivialfitness.user.CasualUser;
import org.trivialfitness.user.PastActivity;
import org.trivialfitness.user.ProfessionalUser;
import org.trivialfitness.user.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;

import org.trivialfitness.activity.activityType.*;

import org.trivialfitness.state.AppState;
import org.trivialfitness.trainingPlan.TrainingPlan;
import org.trivialfitness.trainingPlan.TrainingPlanActivity;

public class AppController {

	private AppState appState;

	private User currentUser;

	public AppController() {
		this.appState = new AppState();
	}

	public boolean isLogged() {
		return currentUser != null;
	}

	public String login(String userId) {
		currentUser = appState.getUser(userId);
		if (currentUser != null) {
			return "Login successful.\n";
		}
		else {
			return "User not found. Please try again.";
		}
	}

	public String getUserName() {
		return currentUser.getName();
	}

	public boolean checkIfUserExists(String userId) {
		return appState.getUser(userId) != null;
	}

	public String registerProfessionalUser(String userId, String name, String address, String email, double weight) {

		ProfessionalUser user = new ProfessionalUser(userId, name, address, email, weight);
		if (appState.addUser(user)) {
			return "User registered successfully. Welcome, " + user.getName() + "!";
		}
		else {
			return "User id already exists. Please try again.";
		}
	}

	public String registerAmateurUser(String userId, String name, String address, String email, double weight) {

		AmateurUser user = new AmateurUser(userId, name, address, email, weight);
		if (appState.addUser(user)) {
			return "User registered successfully. Welcome, " + user.getName() + "!";
		}
		else {
			return "User id already exists. Please try again.";
		}
	}

	public String registerCasualUser(String userId, String name, String address, String email, double weight) {

		CasualUser user = new CasualUser(userId, name, address, email, weight);
		if (appState.addUser(user)) {
			return "User registered successfully. Welcome, " + user.getName() + "!";
		}
		else {
			return "User id already exists. Please try again.";
		}
	}

	public void logout() {
		currentUser = null;
	}

	public String viewPastActivities() {
		StringBuilder sb = new StringBuilder();
		sb.append("Past activities:\n");
		for (int i = 0; i < currentUser.getPastActivities().size(); i++) {
			sb.append("\t" + currentUser.getPastActivities().get(i).getActivity().getActivityName() + " on "
					+ currentUser.getPastActivities().get(i).getDate() + " <-> "
					+ currentUser.getPastActivities().get(i).getActivity().getActivityAttributesString() + ";\n");
		}

		return sb.toString();

	}

	public String viewTrainingPlans() {
		StringBuilder sb = new StringBuilder();
		sb.append("Training plans:\n");
		for (int i = 0; i < currentUser.getTrainingPlans().size(); i++) {
			sb.append("\nTraining plan from " + currentUser.getTrainingPlans().get(i).getStartingDate() + " to "
					+ currentUser.getTrainingPlans().get(i).getEndingDate() + " with "
					+ currentUser.getTrainingPlans().get(i).getActivities().size() + " exercises:\n");
			for (TrainingPlanActivity trainingPlanActivity : currentUser.getTrainingPlans().get(i).getActivities()) {
				sb.append("\t" + trainingPlanActivity.getActivity().getActivityName() + " on "
						+ trainingPlanActivity.getWeekDay() + ";\n");
			}
		}

		return sb.toString();
	}

	public List<String> getAvailableActivitiesTypesNames() {

		List<String> activityType = AppState.getAvailableActivitiesTypesNames();

		activityType.sort(null);
		return activityType;
	}

	public List<String> getActivitiesFromSpecificType(int type) {
		return AppState.getActivitiesFromSpecificType(type - 1);
	}

	public String addNewActivity(int average_heart_rate_value, int durationValue, int distanceValue, int altimetryValue,
			int repetitions, int weightValue, LocalDate date, String activityName, String activityType) {

		PastActivity pastActivity;
		BiFunction<Integer, Integer, Activity> activityCreator = appState.getActivityCreator(activityName);
		if (activityCreator == null) {
			return "Activity not found.";
		}
		if (activityType.equals("Distance")) {
			Activity newActivity = activityCreator.apply(distanceValue, 0);
			pastActivity = new PastActivity(newActivity, average_heart_rate_value, durationValue, date, 0);
			currentUser.addPastActivity(pastActivity);
			return "Activity added successfully.";
		}
		else if (activityType.equals("DistanceAltimetry")) {
			Activity newActivity = activityCreator.apply(distanceValue, altimetryValue);
			pastActivity = new PastActivity(newActivity, average_heart_rate_value, durationValue, date, 0);
			currentUser.addPastActivity(pastActivity);
			return "Activity added successfully.";
		}
		else if (activityType.equals("Repetition")) {
			Activity newActivity = activityCreator.apply(repetitions, 0);
			pastActivity = new PastActivity(newActivity, average_heart_rate_value, durationValue, date, 0);
			currentUser.addPastActivity(pastActivity);
			return "Activity added successfully.";
		}
		else if (activityType.equals("RepetitionWeight")) {
			Activity newActivity = activityCreator.apply(repetitions, weightValue);
			pastActivity = new PastActivity(newActivity, average_heart_rate_value, durationValue, date, 0);
			currentUser.addPastActivity(pastActivity);
			return "Activity added successfully.";
		}
		else {
			return "Activity not found.";
		}
	}

	public LocalDate getTime() {
		return appState.getCurrentDate();
	}

	public String activityRepetitionWeightName(int activity) {
		return AppState.getActivityRepetitionWeightName(activity);
	}

	public String activityRepetitionName(int activity) {
		return AppState.getActivityRepetitionName(activity);
	}

	public String activityDistanceAltimetryName(int activity) {
		return AppState.getActivityDistanceAltimetryName(activity);
	}

	public String activityDistanceName(int activity) {
		return AppState.getActivityDistanceName(activity);
	}

	public String saveStatus() {
		return appState.saveProgress();
	}

	public String advanceInTime(int days) {
		appState.advanceDays(days);
		return "Time advanced successfully. Current date: " + appState.getCurrentDate();
	}

	public TrainingPlan createTrainingPlan(LocalDate startingDate, LocalDate endingDate) {
		TrainingPlan trainingPlan = appState.getNewTrainingPlan(startingDate, endingDate);
		return trainingPlan;

	}

	public String addActivityToTrainingPlan(TrainingPlan trainingPlan, String activityName, DayOfWeek weekDay,
			int distanceValue, int altimetryValue, int repetitions, int weightValue, String activityType) {
		BiFunction<Integer, Integer, Activity> activityCreator = appState.getActivityCreator(activityName);

		if (activityCreator == null) {
			return "Activity not found.";
		}
		if (activityType.equals("Distance")) {
			Activity newActivity = activityCreator.apply(distanceValue, 0);
			trainingPlan.addActivity(new TrainingPlanActivity(newActivity, weekDay));
		}
		else if (activityType.equals("DistanceAltimetry")) {
			Activity newActivity = activityCreator.apply(distanceValue, altimetryValue);
			trainingPlan.addActivity(new TrainingPlanActivity(newActivity, weekDay));
		}
		else if (activityType.equals("Repetition")) {
			Activity newActivity = activityCreator.apply(repetitions, 0);
			trainingPlan.addActivity(new TrainingPlanActivity(newActivity, weekDay));
		}
		else if (activityType.equals("RepetitionWeight")) {
			Activity newActivity = activityCreator.apply(repetitions, weightValue);
			trainingPlan.addActivity(new TrainingPlanActivity(newActivity, weekDay));
		}
		else {
			return "Activity not found.";
		}
		System.out.println(trainingPlan.getActivities().size());
		return "Activity added successfully.";

	}

	public void addTrainingPlanToUser(TrainingPlan trainingPlan) {
		currentUser.addTrainingPlan(trainingPlan);
	}

	public String checkDistanceTraveled(LocalDate beginDate, LocalDate endDate, boolean isDistance) {
		int distance = 0;
		int altimetry = 0;
		for (PastActivity activity : currentUser.getPastActivities()) {
			if ((beginDate == null && endDate == null)
					|| activity.getDate().isAfter(beginDate) && activity.getDate().isBefore(endDate)) {
				if (activity.getActivity() instanceof DistanceActivity) {
					distance += ((DistanceActivity) activity.getActivity()).getDistanceKm();
				}
				else if (activity.getActivity() instanceof DistanceAltimetryActivity) {
					distance += ((DistanceAltimetryActivity) activity.getActivity()).getDistanceKm();
					altimetry += ((DistanceAltimetryActivity) activity.getActivity()).getHeightMt();
				}
			}
		}

		if (isDistance) {
			return "Total distance traveled between " + (beginDate == null ? "the beggining" : beginDate) + " and "
					+ (endDate == null ? "now" : endDate) + " is " + distance + " km.";

		}
		else {

			return "Total altimetry climbed between " + (beginDate == null ? "the beggining" : beginDate) + " and "
					+ (endDate == null ? "now" : endDate) + " is " + altimetry + " mt.";
		}

	}

	public String checkMostFamousActivityType() {
		List<String> activityTypeNames = appState.getAvailableActivitiesTypesNames();
		int[] activityType = new int[activityTypeNames.size()];
		// cheking for all the users
		for (User user : appState.getUsers()) {
			for (PastActivity activity : user.getPastActivities()) {
				activityType[activityTypeNames.indexOf(activity.getActivity().getActivityTypeName())] += 1;
			}
		}

		int max = 0;
		int index = 0;
		for (int i = 0; i < activityType.length; i++) {
			if (activityType[i] > max) {
				max = activityType[i];
				index = i;
			}
		}
		return "Most famous activity type is " + appState.getAvailableActivitiesTypesNames().get(index) + " with " + max
				+ " activities.";
	}

	public String checkUserStatus() {
		int pastActivities = currentUser.getPastActivities().size();
		int trainingPlans = currentUser.getTrainingPlans().size();
		StringBuilder sb = new StringBuilder();
		sb.append("User status:\n");
		sb.append("\tNumber of past activities: " + pastActivities + ";\n");
		sb.append("\tNumber of training plans: " + trainingPlans + ";\n");
		double calories = 0;
		for (PastActivity activity : currentUser.getPastActivities()) {
			calories += activity.getActivity().calculateCalories(currentUser);
		}
		sb.append("\tTotal calories spent: " + calories + ";\n");
		sb.append("\tAverage heart rate: " + currentUser.getAverageHeartRate() + ";\n");

		return sb.toString();
	}

	public String checkMostCaloriesBurned(LocalDate beginDate, LocalDate endDate) {
		double maxCalories = 0;
		String maxCaloriesUser = "";
		for (User user : appState.getUsers()) {
			double calories = 0;
			for (PastActivity activity : user.getPastActivities()) {
				if ((beginDate == null && endDate == null)
						|| activity.getDate().isAfter(beginDate) && activity.getDate().isBefore(endDate)) {
					calories += activity.getActivity().calculateCalories(user);
				}
			}
			if (calories > maxCalories) {
				maxCalories = calories;
				maxCaloriesUser = user.getName();
			}
		}

		return "User " + maxCaloriesUser + " burned the most calories between "
				+ (beginDate == null ? "the beggining" : beginDate) + " and " + (endDate == null ? "now" : endDate)
				+ " with " + String.format("%.2f", maxCalories) + " calories.";

	}

	public String checkUserWithMostActivities(LocalDate beginDate, LocalDate endDate) {
		int maxActivities = 0;
		String maxActivitiesUser = "";
		for (User user : appState.getUsers()) {
			int activities = 0;
			for (PastActivity activity : user.getPastActivities()) {
				if ((beginDate == null && endDate == null)
						|| activity.getDate().isAfter(beginDate) && activity.getDate().isBefore(endDate)) {
					activities += 1;
				}
			}
			if (activities > maxActivities) {
				maxActivities = activities;
				maxActivitiesUser = user.getName();
			}
		}

		return "User " + maxActivitiesUser + " has the most activities between "
				+ (beginDate == null ? "the beggining" : beginDate) + " and " + (endDate == null ? "now" : endDate)
				+ " with " + maxActivities + " activities.";
	}

}
