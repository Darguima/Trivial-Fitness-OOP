package org.trivialfitness.view;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.trivialfitness.activity.activityType.Activity;
import org.trivialfitness.controler.AppController;
import org.trivialfitness.trainingPlan.TrainingPlan;

public class Handlers {

	private Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

	public Handlers() {

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
		System.out.println("3. Save");
		System.out.println("0. Exit\n");
		System.out.print("Choose an option: ");
	}

	public void showUserMenu(AppController controller) {
		System.out.println("\nWelcome, " + controller.getUserName());
		System.out.println("\n1. View Training Plans");
		System.out.println("2. View Activities");
		System.out.println("3. Add New Activity");
		System.out.println("4. Add New Training Plan");
		System.out.println("5. Generate Training Plan");
		System.out.println("6. Advance Time");
		System.out.println("7. Check distance traveled");
		System.out.println("8. Check altimetry total");
		System.out.println("9. Check most famous activity type");
		System.out.println("10. Check status");
		System.out.println("11. Check User with most calories burned");
		System.out.println("12. Check User with most activities done");
		System.out.println("13. Check most hard workout");
		System.out.println("14. Save");
		System.out.println("0. Logout\n");
		System.out.print("Choose an option: ");
	}

	public void showError(String message) {
		System.err.println(message);
	}

	public void handleSaveStatus(AppController controller) {
		clearConsole();
		String message = controller.saveStatus();
		showMessage(message);

	}

	public void handleAdvanceTime(AppController controller) {
		clearConsole();
		String time = getUserInput("Enter the number of days to advance: ");
		int timeValue;
		try {
			timeValue = Integer.parseInt(time);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid time value. Advancing time failed.");
			return;
		}
		String message = controller.advanceInTime(timeValue);
		handleSaveStatus(controller);
		clearConsole();
		showMessage(message);
	}

	public boolean handleLogin(AppController controller) {
		clearConsole();
		String userId = getUserInput("Enter your user ID: ");
		String message = controller.login(userId);
		boolean isLoggedIn = controller.isLogged();
		clearConsole();
		showMessage(message);
		return isLoggedIn;
	}

	public void handleRegister(AppController controller) {
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
		String weight = getUserInput("Enter your weight (in kg): ");
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

	public void handleViewPastActivities(AppController controller) {
		clearConsole();
		String activities_String = controller.viewPastActivities();
		showMessage(activities_String);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();

	}

	public void handleAddNewActivity(AppController controller) {
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
				handle_new_distance_activity(activity, average_heart_rate_value, durationValue, date, controller);
				break;
			case 2:
				handle_new_distance_and_altimetry_activity(activity, average_heart_rate_value, durationValue, date,
						controller);
				break;
			case 3:
				handle_new_repetitions_activity(activity, average_heart_rate_value, durationValue, date, controller);
				break;
			case 4:
				handle_new_weight_repetitions_activity(activity, average_heart_rate_value, durationValue, date,
						controller);
				break;
			default:
				clearConsole();
				showMessage("Invalid activity type. Adding activity failed.");
				return;
		}

	}

	public void handle_new_distance_activity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date, AppController controller) {
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
		String message = controller.addNewActivity(average_heart_rate_value, durationValue, distanceValue, 0, 0, 0,
				date, activityName, "Distance");
		clearConsole();
		showMessage(message);
	}

	public void handle_new_distance_and_altimetry_activity(int activity, int average_heart_rate_value,
			int durationValue, LocalDate date, AppController controller) {
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
		String message = controller.addNewActivity(average_heart_rate_value, durationValue, distanceValue,
				altimetryValue, 0, 0, date, activityName, "DistanceAltimetry");
		clearConsole();
		showMessage(message);
	}

	public void handle_new_repetitions_activity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date, AppController controller) {
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
		String message = controller.addNewActivity(average_heart_rate_value, durationValue, 0, 0, repetitionsValue, 0,
				date, activityName, "Repetition");
		clearConsole();
		showMessage(message);
	}

	public void handle_new_weight_repetitions_activity(int activity, int average_heart_rate_value, int durationValue,
			LocalDate date, AppController controller) {
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
		String message = controller.addNewActivity(average_heart_rate_value, durationValue, 0, 0, repetitionsValue,
				weightValue, date, activityName, "RepetitionWeight");
		clearConsole();
		showMessage(message);

	}

	public void handleViewTrainingPlans(AppController controller) {
		clearConsole();
		String trainingPlans_String = controller.viewTrainingPlans();
		showMessage(trainingPlans_String);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();
	}

	public void handleCreateTrainingPlan(AppController controller) {
		clearConsole();

		String number_of_activites = getUserInput("Enter the number of activities in the training plan: ");
		int number_of_activites_value;
		try {
			number_of_activites_value = Integer.parseInt(number_of_activites);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid number of activities. Creating training plan failed.");
			return;
		}
		if (number_of_activites_value < 1) {
			clearConsole();
			showMessage("Invalid number of activities. Creating training plan failed.");
			return;
		}
		String begin_date = getUserInput("Enter the beginning date of the training plan (yyyy-MM-dd): ");
		LocalDate begin_date_value = null;
		try {
			begin_date_value = LocalDate.parse(begin_date);
		}
		catch (DateTimeParseException e) {
			clearConsole();
			showMessage("Invalid date format. Creating training plan failed.");
			return;
		}
		clearConsole();
		String end_date = getUserInput("Enter the ending date of the training plan (yyyy-MM-dd): ");
		LocalDate end_date_value = null;
		try {
			end_date_value = LocalDate.parse(end_date);
		}
		catch (DateTimeParseException e) {
			clearConsole();
			showMessage("Invalid date format. Creating training plan failed.");
			return;
		}
		if (end_date_value.isBefore(begin_date_value)) {
			clearConsole();
			showMessage("Invalid date range. Creating training plan failed.");
			return;
		}

		TrainingPlan trainingPlan = controller.createTrainingPlan(begin_date_value, end_date_value);

		for (int i = 0; i < number_of_activites_value; i++) {
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
			String activityDay = getUserInput("Enter the day of the week for the activity (1-7): ");
			DayOfWeek activityDayValue;
			try {
				activityDayValue = DayOfWeek.of(Integer.parseInt(activityDay));
			}
			catch (NumberFormatException e) {
				clearConsole();
				showMessage("Invalid day value. Adding activity failed.");
				return;
			}
			if (activityDayValue.getValue() < 1 || activityDayValue.getValue() > 7) {
				clearConsole();
				showMessage("Invalid day value. Adding activity failed.");
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
					handle_new_distance_activity_tp(activity, activityDayValue, controller, trainingPlan);
					break;
				case 2:
					handle_new_distance_and_altimetry_activity_tp(activity, activityDayValue, controller, trainingPlan);
					break;
				case 3:
					handle_new_repetitions_activity_tp(activity, activityDayValue, controller, trainingPlan);
					break;
				case 4:
					handle_new_weight_repetitions_activity_tp(activity, activityDayValue, controller, trainingPlan);
					break;
				default:
					clearConsole();
					showMessage("Invalid activity type. Adding activity failed.");
					return;
			}

		}

		controller.addTrainingPlanToUser(trainingPlan);
		clearConsole();
		showMessage("Training plan created successfully.");

	}

	public void handle_new_distance_activity_tp(int activity, DayOfWeek activityDay, AppController controller,
			TrainingPlan trainingPlan) {
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
		String message = controller.addActivityToTrainingPlan(trainingPlan, activityName, activityDay, distanceValue, 0,
				0, 0, "Distance");
		;
		clearConsole();
		showMessage(message);
	}

	public void handle_new_repetitions_activity_tp(int activity, DayOfWeek activityDay, AppController controller,
			TrainingPlan trainingPlan) {
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
		String message = controller.addActivityToTrainingPlan(trainingPlan, activityName, activityDay, 0, 0,
				repetitionsValue, 0, "Repetition");
		clearConsole();
		showMessage(message);
	}

	public void handle_new_distance_and_altimetry_activity_tp(int activity, DayOfWeek activityDay,
			AppController controller, TrainingPlan trainingPlan) {
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
		String message = controller.addActivityToTrainingPlan(trainingPlan, activityName, activityDay, distanceValue,
				altimetryValue, 0, 0, "DistanceAltimetry");
		clearConsole();
		showMessage(message);
	}

	public void handle_new_weight_repetitions_activity_tp(int activity, DayOfWeek activityDay, AppController controller,
			TrainingPlan trainingPlan) {
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
		String message = controller.addActivityToTrainingPlan(trainingPlan, activityName, activityDay, 0, 0,
				repetitionsValue, weightValue, "RepetitionWeight");
		clearConsole();
		showMessage(message);
	}

	public void handle_check_distance_traveled(AppController controller, boolean distance) {
		clearConsole();

		String begin_date = getUserInput(
				"Enter the beginning date of the period (yyyy-MM-dd) or press enter to check from the beginning: ");
		LocalDate begin_date_value = null;
		if (!begin_date.equals("")) {
			try {
				begin_date_value = LocalDate.parse(begin_date);
			}
			catch (DateTimeParseException e) {
				clearConsole();
				showMessage("Invalid date format. Checking distance failed.");
				return;
			}
			clearConsole();
		}
		String end_date = getUserInput(
				"Enter the ending date of the period (yyyy-MM-dd) or press enter to check until today: ");
		LocalDate end_date_value = null;
		if (!end_date.equals("")) {
			try {
				end_date_value = LocalDate.parse(end_date);
			}
			catch (DateTimeParseException e) {
				clearConsole();
				showMessage("Invalid date format. Checking distance failed.");
				return;
			}
		}
		if ((begin_date_value == null && end_date_value != null) || (begin_date_value != null && end_date_value == null)
				|| (begin_date_value != null && end_date_value != null && end_date_value.isBefore(begin_date_value))) {
			clearConsole();
			showMessage("Invalid date range. Checking distance failed.");
			return;
		}

		String message = controller.checkDistanceTraveled(begin_date_value, end_date_value, distance);
		clearConsole();
		showMessage(message);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();

	}

	public void handle_check_status(AppController controller) {
		clearConsole();
		String message = controller.checkUserStatus();
		showMessage(message);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();
	}

	public void handle_check_most_famous_activity_type(AppController controller) {
		clearConsole();
		String message = controller.checkMostFamousActivityType();
		showMessage(message);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();
	}

	public void handle_check_most_calories_burned(AppController controller) {
		clearConsole();
		// getting begin date and end date if nedded
		String begin_date = getUserInput(
				"Enter the beginning date of the period (yyyy-MM-dd) or press enter to check from the beginning: ");
		LocalDate begin_date_value = null;
		if (!begin_date.equals("")) {
			try {
				begin_date_value = LocalDate.parse(begin_date);
			}
			catch (DateTimeParseException e) {
				clearConsole();
				showMessage("Invalid date format. Checking distance failed.");
				return;
			}
			clearConsole();
		}
		clearConsole();
		String end_date = getUserInput(
				"Enter the ending date of the period (yyyy-MM-dd) or press enter to check until today: ");
		LocalDate end_date_value = null;
		if (!end_date.equals("")) {
			try {
				end_date_value = LocalDate.parse(end_date);
			}
			catch (DateTimeParseException e) {
				clearConsole();
				showMessage("Invalid date format. Checking distance failed.");
				return;
			}
		}
		if ((begin_date_value == null && end_date_value != null) || (begin_date_value != null && end_date_value == null)
				|| (begin_date_value != null && end_date_value != null && end_date_value.isBefore(begin_date_value))) {
			clearConsole();
			showMessage("Invalid date range. Checking distance failed.");
			return;
		}

		clearConsole();
		String message = controller.checkMostCaloriesBurned(begin_date_value, end_date_value);
		showMessage(message);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();
	}

	public void handle_check_user_with_most_activities(AppController controller) {
		clearConsole();

		// asking begin and end date if nedeed
		String begin_date = getUserInput(
				"Enter the beginning date of the period (yyyy-MM-dd) or press enter to check from the beginning: ");
		LocalDate begin_date_value = null;
		if (!begin_date.equals("")) {
			try {
				begin_date_value = LocalDate.parse(begin_date);
			}
			catch (DateTimeParseException e) {
				clearConsole();
				showMessage("Invalid date format. Checking distance failed.");
				return;
			}
			clearConsole();
		}
		clearConsole();
		String end_date = getUserInput(
				"Enter the ending date of the period (yyyy-MM-dd) or press enter to check until today: ");
		LocalDate end_date_value = null;
		if (!end_date.equals("")) {
			try {
				end_date_value = LocalDate.parse(end_date);
			}
			catch (DateTimeParseException e) {
				clearConsole();
				showMessage("Invalid date format. Checking distance failed.");
				return;
			}
		}
		if ((begin_date_value == null && end_date_value != null) || (begin_date_value != null && end_date_value == null)
				|| (begin_date_value != null && end_date_value != null && end_date_value.isBefore(begin_date_value))) {
			clearConsole();
			showMessage("Invalid date range. Checking distance failed.");
			return;
		}

		String message = controller.checkUserWithMostActivities(begin_date_value, end_date_value);
		showMessage(message);
		showMessage("Press any key to continue...");
		scanner.nextLine();
		clearConsole();
	}

	public void handleGenerateTrainingPlan(AppController controller) {
		clearConsole();
		// public TrainingPlan(User user, LocalDate startingDate, LocalDate endingDate,
		// int maxActivitiesPerDays,
		// int maxDifferentActivities, int activitiesWeeklyFreq, int caloriesGoal, boolean
		// hasHard,
		// Class<? extends Activity> activitiesType)
		String begin_date = getUserInput("Enter the beginning date of the training plan (yyyy-MM-dd): ");
		LocalDate begin_date_value = null;
		try {
			begin_date_value = LocalDate.parse(begin_date);
		}
		catch (DateTimeParseException e) {
			clearConsole();
			showMessage("Invalid date format. Generating training plan failed.");
			return;
		}
		clearConsole();
		String end_date = getUserInput("Enter the ending date of the training plan (yyyy-MM-dd): ");
		LocalDate end_date_value = null;
		try {
			end_date_value = LocalDate.parse(end_date);
		}
		catch (DateTimeParseException e) {
			clearConsole();
			showMessage("Invalid date format. Generating training plan failed.");
			return;
		}
		if (end_date_value.isBefore(begin_date_value)) {
			clearConsole();
			showMessage("Invalid date range. Generating training plan failed.");
			return;
		}
		clearConsole();
		String max_activities_per_day = getUserInput("Enter the maximum number of activities per day: ");
		int max_activities_per_day_value;
		try {
			max_activities_per_day_value = Integer.parseInt(max_activities_per_day);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid number of activities per day. Generating training plan failed.");
			return;
		}

		String max_different_activities = getUserInput("Enter the maximum number of different activities: ");
		int max_different_activities_value;
		try {
			max_different_activities_value = Integer.parseInt(max_different_activities);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid number of different activities. Generating training plan failed.");
			return;
		}
		clearConsole();
		String activities_weekly_freq = getUserInput("Enter the activity frequency: ");
		int activities_weekly_freq_value;
		try {
			activities_weekly_freq_value = Integer.parseInt(activities_weekly_freq);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid activity frequency. Generating training plan failed.");
			return;
		}
		clearConsole();
		String calories_goal = getUserInput("Enter the calories goal: ");
		int calories_goal_value;
		try {
			calories_goal_value = Integer.parseInt(calories_goal);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid calories goal. Generating training plan failed.");
			return;
		}

		clearConsole();
		String has_hard = getUserInput("Do you want to include hard activities? (yes/no): ");
		boolean has_hard_value;
		if (has_hard.equals("yes")) {
			has_hard_value = true;
		}
		else if (has_hard.equals("no")) {
			has_hard_value = false;
		}
		else {
			clearConsole();
			showMessage("Invalid option. Generating training plan failed.");
			return;
		}
		clearConsole();
		int size = 0;
		for (String activityTypeName : controller.getAvailableActivitiesTypesNames()) {
			size++;
			showMessage(size + ". " + activityTypeName);
		}
		showMessage("");
		String activities_type_choose = getUserInput("Choose an activity type: ");
		int activities_type;
		try {
			activities_type = Integer.parseInt(activities_type_choose);
		}
		catch (NumberFormatException e) {
			clearConsole();
			showMessage("Invalid activity type. Generating training plan failed.");
			return;
		}
		if (activities_type < 1 || activities_type > size) {
			clearConsole();
			showMessage("Invalid activity type. Generating training plan failed.");
			return;
		}
		clearConsole();
		String message = controller.getNewTrainingPlan(begin_date_value, end_date_value, max_activities_per_day_value,
				max_different_activities_value, activities_weekly_freq_value, calories_goal_value, has_hard_value,
				activities_type);

		showMessage(message);

	}

	public void handle_check_most_hard_workout(AppController controller) {
		clearConsole();
		String message = controller.getTrainingPlanMostCalories();
		showMessage(message);
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
