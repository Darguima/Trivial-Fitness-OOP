package org.trivialfitness.controler;

import org.trivialfitness.user.AmateurUser;
import org.trivialfitness.user.CasualUser;
import org.trivialfitness.user.PastActivity;
import org.trivialfitness.user.ProfessionalUser;
import org.trivialfitness.user.User;
import org.trivialfitness.view.View;

import java.time.LocalDate;
import java.util.List;

import org.trivialfitness.activity.Activity;
import org.trivialfitness.activity.BenchPress;
import org.trivialfitness.activity.MountainBike;
import org.trivialfitness.activity.PushUps;
import org.trivialfitness.activity.Rowing;
import org.trivialfitness.state.AppState;
//import org.trivialfitness.trainingPlan.TrainingPlan;
import org.trivialfitness.trainingPlan.TrainingPlan;
import org.trivialfitness.trainingPlan.TrainingPlanActivity;

public class AppController {

	private AppState appState;

	private User currentUser;

	public AppController(AppState appState) {
		this.appState = new AppState(appState);
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

	// String userType, String userId, String name, String address, String email, double
	// weight

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
					+ currentUser.getPastActivities().get(i).getDate() + ";\n");
		}
		return sb.toString();

	}

	public List<String> getAvailableActivitiesTypesNames() {
		List<String> activityType = List.of("Distance", "Distance and Altimetry", "Repetitions",
				"Repetitions with Weight");
		return activityType;
	}

	public List<String> getActivitiesFromSpecificType(int type) {
		return appState.getActivitiesFromSpecificType(type);
	}

	public String addNewDistanceActivity(int activity, int average_heart_rate_value, int durationValue, LocalDate date,
			int distanceValue) {
		// getting the activity name from the hashmap that contains the distance
		// activities (number 1 )
		String activityName = appState.getActivityDistanceName(activity);
		// we only have rowing att the moment
		System.out.println("Activity name: " + activityName);
		System.out.println("Distance value: " + distanceValue);
		switch (activityName) {
			case "Rowing":
				Rowing rowing = new Rowing(distanceValue);
				PastActivity pastActivity = new PastActivity(rowing, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";
			default:
				return "Activity not found.";
		}

	}

	public LocalDate getTime() {
		return appState.getCurrentDate();
	}

}

/*
	


*/