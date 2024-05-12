package org.trivialfitness.view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.trivialfitness.controler.AppController;

public class View {

	private AppController controller;

	private boolean isLoggedIn = false;

	private Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

	private boolean wantsToExit = false;

	private Handlers handlers;

	public View() {
		this.controller = new AppController();
		this.handlers = new Handlers();

	}

	public void run() {
		String option = "";
		handlers.clearConsole();
		handlers.showMessage(" _____     _       _       _   _____ _ _");
		handlers.showMessage("|_   _| __(_)_   _(_) __ _| | |  ___(_) |_ _ __   ___  ___ ___");
		handlers.showMessage("  | || '__| \\ \\ / / |/ _` | | | |_  | | __| '_ \\ / _ \\/ __/ __|");
		handlers.showMessage("  | || |  | |\\ V /| | (_| | | |  _| | | |_| | | |  __/\\__ \\__ \\");
		handlers.showMessage("  |_||_|  |_| \\_/ |_|\\__,_|_| |_|   |_|\\__|_| |_|\\___||___/___/");

		do {

			if (!isLoggedIn) {
				handlers.showStartMenu();
			}
			else {
				handlers.showUserMenu(controller);
			}
			option = handlers.getUserInput("");

			if (!isLoggedIn) {
				switch (option) {
					case "1":
						isLoggedIn = handlers.handleLogin(controller);
						break;
					case "2":
						handlers.handleRegister(controller);
						break;
					case "0":
						handlers.clearConsole();
						handlers.handleSaveStatus(controller);
						System.out.println("Thanks for using Trivial Fitness! We hope to see you soon.");
						wantsToExit = true;

						break;
					default:
						handlers.clearConsole();
						System.out.println("Invalid option. Please try again.");
				}
			}
			else {
				switch (option) {
					case "1":
						handlers.handleViewTrainingPlans(controller);
						break;
					case "2":
						handlers.handleViewPastActivities(controller);
						break;
					case "3":
						handlers.handleAddNewActivity(controller);
						break;
					case "4":
						handlers.handleCreateTrainingPlan(controller);
						break;
					case "5":
						handlers.handleGenerateTrainingPlan(controller);
						break;
					case "6":
						handlers.handleAdvanceTime(controller);
						break;
					case "7":
						handlers.handle_check_distance_traveled(controller, true);
						break;
					case "8":
						handlers.handle_check_distance_traveled(controller, false);
						break;
					case "9":
						handlers.handle_check_most_famous_activity_type(controller);
						break;

					case "10":
						handlers.handle_check_status(controller);
						break;

					case "11":
						handlers.handle_check_most_calories_burned(controller);
						break;

					case "12":
						handlers.handle_check_user_with_most_activities(controller);
						break;

					case "13":
						handlers.handle_check_most_hard_workout(controller);
						break;

					case "14":
						handlers.clearConsole();
						handlers.handleSaveStatus(controller);
						handlers.clearConsole();
						break;
					case "0":
						isLoggedIn = false;
						wantsToExit = false;
						handlers.clearConsole();
						controller.logout();
						System.out.println("Logged out successfully.");
						break;
					default:
						handlers.clearConsole();
						System.out.println("Invalid option. Please try again.");
						break;
				}
			}
		}
		while (!option.equals("0") || !wantsToExit);
		scanner.close();
	}

}
