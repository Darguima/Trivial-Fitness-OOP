package org.trivialfitness.controler;

import org.trivialfitness.user.AmateurUser;
import org.trivialfitness.user.CasualUser;
import org.trivialfitness.user.PastActivity;
import org.trivialfitness.user.ProfessionalUser;
import org.trivialfitness.user.User;
import java.time.LocalDate;
import java.util.List;

import org.trivialfitness.activity.*;
import org.trivialfitness.state.AppState;
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

		String activityName = appState.getActivityDistanceName(activity);

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

	public String addNewDistanceAltimetryActivity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date, int distanceValue, int altimetryValue) {

		String activityName = appState.getActivityDistanceAltimetryName(activity);

		switch (activityName) {
			case "Mountain Bike":
				MountainBike mountainBike = new MountainBike(distanceValue, altimetryValue);
				PastActivity pastActivity = new PastActivity(mountainBike, average_heart_rate_value, durationValue,
						date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";
			default:
				return "Activity not found.";
		}

	}

	public String addNewRepetitionActivity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date, int repetitions) {

		String activityName = appState.getActivityRepetitionName(activity);
		PastActivity pastActivity;

		switch (activityName) {
			case "Push Ups":
				PushUps pushUps = new PushUps(repetitions);
				pastActivity = new PastActivity(pushUps, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";

			case "Jumping Jacks":
				JumpingJacks jumpingJacks = new JumpingJacks(repetitions);
				pastActivity = new PastActivity(jumpingJacks, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";

			case "Scissors":
				Scissors scissors = new Scissors(repetitions);
				pastActivity = new PastActivity(scissors, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";

			case "Squats":
				Squats squats = new Squats(repetitions);
				pastActivity = new PastActivity(squats, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";

			case "Burpees":
				Burpees burpees = new Burpees(repetitions);
				pastActivity = new PastActivity(burpees, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";

			default:
				return "Activity not found.";
		}
	}

	public String addNewWeightRepetitionsActivity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date, int repetitions, int weightValue) {

		String activityName = appState.getActivityRepetitionWeightName(activity);
		PastActivity pastActivity;

		switch (activityName) {
			case "Bench Press":
				BenchPress benchPress = new BenchPress(repetitions, weightValue);
				pastActivity = new PastActivity(benchPress, average_heart_rate_value, durationValue, date, 0);
				currentUser.addPastActivity(pastActivity);
				return "Activity added successfully.";
			default:
				return "Activity not found.";
		}

	}

	public LocalDate getTime() {
		return appState.getCurrentDate();
	}

	public String getActivityRepetitionWeightName(int activity) {
		return appState.getActivityRepetitionWeightName(activity);
	}

	public String getActivityRepetitionName(int activity) {
		return appState.getActivityRepetitionName(activity);
	}

	public String getActivityDistanceAltimetryName(int activity) {
		return appState.getActivityDistanceAltimetryName(activity);
	}

	public String getActivityDistanceName(int activity) {
		return appState.getActivityDistanceName(activity);
	}

}

/*
	


*/