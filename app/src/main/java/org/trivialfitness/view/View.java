package org.trivialfitness.view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.trivialfitness.controler.AppController;
import org.trivialfitness.state.AppState;

public class View {

	private AppController controller;

	private boolean isLoggedIn = false;

	private Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

	private boolean wantsToExit = false;

	public View(AppState appState) {
		this.controller = new AppController(appState);

	}

	public void run() {
		String option = "";
		clearConsole();
		showMessage(" _____     _       _       _   _____ _ _");
		showMessage("|_   _| __(_)_   _(_) __ _| | |  ___(_) |_ _ __   ___  ___ ___");
		showMessage("  | || '__| \\ \\ / / |/ _` | | | |_  | | __| '_ \\ / _ \\/ __/ __|");
		showMessage("  | || |  | |\\ V /| | (_| | | |  _| | | |_| | | |  __/\\__ \\__ \\");
		showMessage("  |_||_|  |_| \\_/ |_|\\__,_|_| |_|   |_|\\__|_| |_|\\___||___/___/");

		do {

			if (!isLoggedIn) {
				showStartMenu();
			}
			else {
				showUserMenu();
			}
			option = getUserInput("");

			if (!isLoggedIn) {
				switch (option) {
					case "1":
						handleLogin();
						break;
					case "2":
						handleRegister();
						break;
					case "0":
						clearConsole();
						System.out.println("Thanks for using Trivial Fitness! We hope to see you soon.");
						wantsToExit = true;
						break;
					default:
						clearConsole();
						System.out.println("Invalid option. Please try again.");
				}
			}
			else {
				switch (option) {
					case "1":
						handleViewTrainingPlans();
						break;
					case "2":
						handleViewPastActivities();
						break;
					case "3":
						handleAddNewActivity();
						break;
					case "4":
						// handleAddNewTrainingPlan();
						break;
					case "5":
						// handleGenerateTrainingPlan();
						break;
					case "0":
						isLoggedIn = false;
						wantsToExit = false;
						clearConsole();
						controller.logout();
						System.out.println("Logged out successfully.");
						break;
					default:
						clearConsole();
						System.out.println("Invalid option. Please try again.");
						break;
				}
			}
		}
		while (!option.equals("0") || !wantsToExit);
		scanner.close();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public String getUserInput(String prompt) {
		System.out.print(prompt);
		if (scanner.hasNextLine()) {
			return scanner.nextLine();
		}
		return null;
	}

	public void showStartMenu() {
		System.out.println("\n1. Login");
		System.out.println("2. Register");
		System.out.println("0. Exit\n");
		System.out.print("Choose an option: ");
	}

	public void showUserMenu() {
		System.out.println("\n1. View Training Plans");
		System.out.println("2. View Activities");
		System.out.println("3. Add New Activity");
		System.out.println("4. Add New Training Plan");
		System.out.println("0. Logout\n");
		System.out.print("Choose an option: ");
	}

	public void showError(String message) {
		System.err.println(message);
	}

	public void handleLogin() {
		clearConsole();
		String userId = getUserInput("Enter your user ID: ");
		String message = controller.login(userId);
		this.isLoggedIn = controller.isLogged();
		clearConsole();
		showMessage(message);
	}

	public void handleRegister() {
		clearConsole();
		showMessage("Registering a new user...");

		showMessage("Choose the type of user:");
		showMessage("1. Professional User");
		showMessage("2. Amateur User");
		showMessage("3. Casual User");
		String userType = getUserInput("");
		if (!userType.equals("1") && !userType.equals("2") && !userType.equals("3")) {
			clearConsole();
			showMessage("Invalid user type selected. Registration failed.");
			return;
		}

		clearConsole();
		String userId = getUserInput("Enter a new user ID: ");
		if (controller.checkIfUserExists(userId)) {
			clearConsole();
			showMessage("User ID already exists. Registration failed.");
			return;
		}
		String name = getUserInput("Enter your name: ");
		String address = getUserInput("Enter your address: ");
		String email = getUserInput("Enter your email: ");
		String weight = getUserInput("Enter your weight: ");
		double weightValue;

		try {
			weightValue = Double.parseDouble(weight);
		}
		catch (NumberFormatException e) {
			showMessage("Invalid weight value. Registration failed.");
			return;
		}

		String message = "";

		switch (userType) {
			case "1":
				message = controller.registerProfessionalUser(userId, name, address, email, weightValue);
				break;
			case "2":
				message = controller.registerAmateurUser(userId, name, address, email, weightValue);
				break;
			default:
				message = controller.registerCasualUser(userId, name, address, email, weightValue);
				break;
		}

		clearConsole();
		showMessage(message);
	}

	public void handleViewPastActivities() {
		clearConsole();
		String activities_String = controller.viewPastActivities();
		showMessage(activities_String);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();

	}

	public void handleAddNewActivity() {
		clearConsole();
		int size = 0;

		for (String activityTypeName : controller.getAvailableActivitiesTypesNames()) {
			size++;

			showMessage(size + ". " + activityTypeName);
		}
		showMessage("");
		String activity_type_choose = getUserInput("Choose an activity type: ");
		int activity_type;
		try {
			activity_type = Integer.parseInt(activity_type_choose);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid activity type. Adding activity failed.");
			return;
		}
		if (activity_type < 1 || activity_type > size) {
			clearConsole();
			showMessage("Invalid activity type. Adding activity failed.");
			return;
		}
		clearConsole();

		String activityDate = getUserInput("Enter the date of the activity (yyyy-MM-dd): ");
		LocalDate date = null;
		try {
			date = LocalDate.parse(activityDate);
		}
		catch (DateTimeParseException e) {
			clearConsole();
			showMessage("Invalid date format. Adding activity failed.");
			return;

		}

		if (date.isAfter(controller.getTime())) {
			clearConsole();
			showMessage("Are you a time traveler? You can't add activities in the future. Adding activity failed.");
			return;
		}
		String duration = getUserInput("Enter the duration of the activity in minutes: ");
		int durationValue;
		try {
			durationValue = Integer.parseInt(duration);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid duration value. Adding activity failed.");
			return;
		}

		String average_heart_rate = getUserInput("Enter the average heart rate of the activity: ");
		int average_heart_rate_value;
		try {
			average_heart_rate_value = Integer.parseInt(average_heart_rate);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid heart rate value. Adding activity failed.");
			return;
		}
		clearConsole();

		int option = 0;
		for (String activityName : controller.getActivitiesFromSpecificType(activity_type)) {
			option++;
			showMessage(option + ". " + activityName);
		}
		showMessage("");
		String activity_choose = getUserInput("Choose an activity: ");
		int activity;
		try {
			activity = Integer.parseInt(activity_choose);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid activity. Adding activity failed.");
			return;
		}

		if (activity < 1 || activity > option) {
			clearConsole();
			showMessage("Invalid activity. Adding activity failed.");
			return;
		}

		switch (activity_type) {
			case 1:
				handle_new_distance_activity(activity, average_heart_rate_value, durationValue, date);
				break;
			case 2:
				handle_new_distance_and_altimetry_activity(activity, average_heart_rate_value, durationValue, date);
				break;
			case 3:
				handle_new_repetitions_activity(activity, average_heart_rate_value, durationValue, date);
				break;
			case 4:
				handle_new_weight_repetitions_activity(activity, average_heart_rate_value, durationValue, date);
				break;
			default:
				clearConsole();
				showMessage("Invalid activity type. Adding activity failed.");
				return;
		}

	}

	public void handle_new_distance_activity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date) {
		clearConsole();
		String activityName = controller.activityDistanceName(--activity);
		showMessage("Activity: " + activityName);
		String distance = getUserInput("Enter the distance of the activity in km: ");
		int distanceValue;
		try {
			distanceValue = Integer.parseInt(distance);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid distance value. Adding activity failed.");
			return;
		}
		String message = controller.addNewDistanceActivity(--activity, average_heart_rate_value, durationValue, date,
				distanceValue, activityName);
		clearConsole();
		showMessage(message);
	}

	public void handle_new_distance_and_altimetry_activity(int activity, int average_heart_rate_value,
			int durationValue, LocalDate date) {
		clearConsole();
		String activityName = controller.activityDistanceAltimetryName(--activity);
		showMessage("Activity: " + activityName);
		String distance = getUserInput("Enter the distance of the activity in km: ");
		int distanceValue;
		try {
			distanceValue = Integer.parseInt(distance);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid distance value. Adding activity failed.");
			return;
		}
		String altimetry = getUserInput("Enter the altimetry of the activity in meters: ");
		int altimetryValue;
		try {
			altimetryValue = Integer.parseInt(altimetry);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid altimetry value. Adding activity failed.");
			return;
		}
		String message = controller.addNewDistanceAltimetryActivity(--activity, average_heart_rate_value, durationValue,
				date, distanceValue, altimetryValue, activityName);
		clearConsole();
		showMessage(message);
	}

	public void handle_new_repetitions_activity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date) {
		clearConsole();
		String activityName = controller.activityRepetitionName(--activity);
		showMessage("Activity: " + activityName);
		String repetitions = getUserInput("Enter the number of repetitions of the activity: ");
		int repetitionsValue;
		try {
			repetitionsValue = Integer.parseInt(repetitions);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid repetitions value. Adding activity failed.");
			return;
		}
		String message = controller.addNewRepetitionActivity(--activity, average_heart_rate_value, durationValue, date,
				repetitionsValue, activityName);
		clearConsole();
		showMessage(message);
	}

	public void handle_new_weight_repetitions_activity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date) {
		clearConsole();
		String activityName = controller.activityRepetitionWeightName(--activity);
		showMessage("Activity: " + activityName);
		String repetitions = getUserInput("Enter the number of repetitions of the activity:");
		int repetitionsValue;
		try {
			repetitionsValue = Integer.parseInt(repetitions);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid repetitions value. Adding activity failed.");
			return;
		}
		String weight = getUserInput("Enter the weight of the activity in kg: ");
		int weightValue;
		try {
			weightValue = Integer.parseInt(weight);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid weight value. Adding activity failed.");
			return;
		}
		String message = controller.addNewWeightRepetitionsActivity(--activity, average_heart_rate_value, durationValue,
				date, repetitionsValue, weightValue, activityName);
		clearConsole();
		showMessage(message);

	}

	public void handleViewTrainingPlans() {
		clearConsole();
		String trainingPlans_String = controller.viewTrainingPlans();
		showMessage(trainingPlans_String);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();
	}

	public void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		}
		catch (Exception e) {
			System.out.println("Error clearing console: " + e.getMessage());
		}
	}

}
