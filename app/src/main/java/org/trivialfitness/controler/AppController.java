package org.trivialfitness.controler;

import org.trivialfitness.user.AmateurUser;
import org.trivialfitness.user.CasualUser;
import org.trivialfitness.user.PastActivity;
import org.trivialfitness.user.ProfessionalUser;
import org.trivialfitness.user.User;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;

import org.trivialfitness.activity.activityType.*;

import org.trivialfitness.state.AppState;

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
			return "Login successful. Welcome, " + currentUser.getName() + "!";
		}
		else {
			return "User not found. Please try again.";
		}
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
			sb.append("Training plan from " + currentUser.getTrainingPlans().get(i).getStartingDate() + " to "
					+ currentUser.getTrainingPlans().get(i).getEndingDate() + " with "
					+ currentUser.getTrainingPlans().get(i).getActivities().size() + " exercises:\n");
			for (TrainingPlanActivity trainingPlanActivity : currentUser.getTrainingPlans().get(i).getActivities()) {
				sb.append("\t" + trainingPlanActivity.activity.getActivityName() + " on " + trainingPlanActivity.weekDay
						+ ";\n");
			}
		}

		return sb.toString();
	}

	public List<String> getAvailableActivitiesTypesNames() {

		List<String> activityType = appState.getAvailableActivitiesTypesNames();

		activityType.sort(null);
		return activityType;
	}

	public List<String> getActivitiesFromSpecificType(int type) {
		return appState.getActivitiesFromSpecificType(type - 1);
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
		return appState.getActivityRepetitionWeightName(activity);
	}

	public String activityRepetitionName(int activity) {
		return appState.getActivityRepetitionName(activity);
	}

	public String activityDistanceAltimetryName(int activity) {
		return appState.getActivityDistanceAltimetryName(activity);
	}

	public String activityDistanceName(int activity) {
		return appState.getActivityDistanceName(activity);
	}

	public String saveStatus() {
		return appState.saveProgress();
	}

}
