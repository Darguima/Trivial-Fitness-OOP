package org.trivialfitness.view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
						System.out.println("Thanks for using Trivial Fitness! We hope to see you soon.");
						wantsToExit = true;
						break;
					default:
						System.out.println("Invalid option. Please try again.");
				}
			}
			else {
				switch (option) {
					case "1":
						// handleViewTrainingPlans();
						break;
					case "2":
						// handleViewActivities();
						break;
					case "3":
						// handleAddNewActivity();
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
		System.out.println("0. Exit");
		System.out.println("Choose an option: ");
	}

	public void showUserMenu() {
		System.out.println("\n1. View Training Plans");
		System.out.println("2. View Activities");
		System.out.println("3. Add New Activity");
		System.out.println("4. Add New Training Plan");
		System.out.println("0. Logout");
		System.out.println("Choose an option: ");
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
