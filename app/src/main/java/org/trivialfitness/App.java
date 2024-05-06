package org.trivialfitness;

import java.time.LocalDate;
import java.util.List;

import org.trivialfitness.activity.*;
import org.trivialfitness.state.AppState;
import org.trivialfitness.trainingPlan.*;
import org.trivialfitness.user.*;

public class App {

	public static void main(String[] args) {
		AppState appState = new AppState();
		System.out.println("Today is " + appState.getCurrentDate() + ";");

		PushUps pushUps = new PushUps(10);
		BenchPress benchPress = new BenchPress(10, 100);
		MountainBike mountainBike = new MountainBike(10, 100);
		Rowing rowing = new Rowing(10);
		List<Activity> activities = List.of(pushUps, benchPress, mountainBike, rowing);

		System.out.println("PushUps with " + pushUps.getRepetitions() + " repetitions is present on the list of "
				+ activities.size() + " exercises;");

		for (int i = 1; i < 2; i++) {
			System.out.println("\n=====================\nCreating user " + i + ";\n=====================\n");

			User user = new ProfessionalUser("user_" + i, "User_" + i, "Rua " + i, i + "email@mail.mail", i);

			System.out.println("\nThe user '" + user.getName() + "' is a " + user.getUserType() + " with a "
					+ user.calculateFitnessMultiplier() + " fitness multiplier and a average heart rate of "
					+ user.getAverageHeartRate() + " bpm;\n");

			TrainingPlan trainingPlan = new TrainingPlan(appState.getCurrentDate(), appState.getCurrentDate().plusDays(365 / i));

			for (int a = 0; a < activities.size(); a++) {
				Activity activity = activities.get(a);
				trainingPlan.addActivity(
						new TrainingPlanActivity(activity, appState.getCurrentDate().plusDays((a * i)).getDayOfWeek()));
			}

			System.out.println("\nTraining Plan from " + trainingPlan.getStartingDate() + " to "
					+ trainingPlan.getEndingDate() + " with " + trainingPlan.getActivities().size() + " exercises:\n");

			for (TrainingPlanActivity trainingPlanActivity : trainingPlan.getActivities()) {
				System.out.println("\t" + trainingPlanActivity.activity.getActivityName() + " on "
						+ trainingPlanActivity.weekDay + ";");
			}

			user.addTrainingPlan(trainingPlan);
			System.out.println("\nThe user '" + user.getName() + "' has a training plan that starts at "
					+ user.getTrainingPlans().get(0).getStartingDate() + " and ends at "
					+ user.getTrainingPlans().get(0).getEndingDate() + ";");

			appState.addUser(user);
		}

		System.out.println("\n=====================\nEnd\n=====================\n");

		System.out.println("\nToday is " + appState.getCurrentDate() + ";");
		System.out
			.println("This are the first user past activities: " + appState.getUsers().get(0).getPastActivities());
		System.out.println("\n\t-> Gonna add 25 days to the app time;");

		appState.advanceDays(25);
		System.out.println("\nToday is " + appState.getCurrentDate() + ";");

		User user = appState.getUser("user_1");
		System.out.println("This are now the first user past activities: ");
		List<PastActivity> pastActivities = user.getPastActivities();
		for (PastActivity pastActivity : pastActivities) {
			System.out
				.println("\t" + pastActivity.getActivity().getActivityName() + " on " + pastActivity.getDate() + ";");
		}

	}

}
