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

	private Handlers handlers;

	public View(AppState appState) {
		this.controller = new AppController(appState);
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
				handlers.showUserMenu();
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
						// handerls.handleCreateTrainingPlan();
						break;
					case "5":
						// handlers.handleGenerateTrainingPlan();
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
