package org.trivialfitness;

import java.time.LocalDate;
import java.util.List;

import org.trivialfitness.activity.Activity;
import org.trivialfitness.activity.BenchPress;
import org.trivialfitness.activity.MountainBike;
import org.trivialfitness.activity.PushUps;
import org.trivialfitness.activity.Rowing;
import org.trivialfitness.trainingPlan.TrainingPlan;
import org.trivialfitness.trainingPlan.TrainingPlanActivity;
import org.trivialfitness.user.ProfessionalUser;

public class App {

	public static void main(String[] args) {
		ProfessionalUser userDwarf = new ProfessionalUser("abaixo_os_anoes", "Não Anão", "Rua onde não moram anões",
				"anao_branco@chama_anao.verde", 75);

		System.out.println("\nThe user '" + userDwarf.getName() + "' is a " + userDwarf.getUserType() + " with a "
				+ userDwarf.calculateFitnessMultiplier() + " fitness multiplier and a average heart rate of "
				+ userDwarf.getAverageHeartRate() + " bpm;\n");

		PushUps pushUps = new PushUps(10);
		BenchPress benchPress = new BenchPress(10, 100);
		MountainBike mountainBike = new MountainBike(10, 100);
		Rowing rowing = new Rowing(10);
		List<Activity> activities = List.of(pushUps, benchPress, mountainBike, rowing);

		System.out.println("The exercise PushUps with " + pushUps.getRepetitions() + " repetitions burned "
				+ pushUps.calculateCalories(userDwarf) + " calories;");
		System.out.println("The exercise BenchPress with " + benchPress.getRepetitions() + " repetitions and "
				+ benchPress.getWeight() + "kg buerned " + benchPress.calculateCalories(userDwarf) + " calories;");

		System.out.println("The exercise MountainBike with " + mountainBike.getDistanceKm() + "km, "
				+ mountainBike.getElevationGain() + "m buerned " + mountainBike.calculateCalories(userDwarf)
				+ " calories;");

		System.out.println("The exercise Rowing with " + rowing.getDistanceKm() + "km buerned "
				+ rowing.calculateCalories(userDwarf) + " calories;");

		TrainingPlan trainingPlan = new TrainingPlan(LocalDate.now(), LocalDate.now().plusDays(7));

		for (int i = 0; i < activities.size(); i++) {
			Activity activity = activities.get(i);
			trainingPlan.addActivity(new TrainingPlanActivity(activity, LocalDate.now().plusDays(i).getDayOfWeek()));
		}

		System.out.println("\nTraining Plan from " + trainingPlan.getStartingDate() + " to "
				+ trainingPlan.getEndingDate() + " with " + trainingPlan.getActivities().size() + " exercises:\n");

		for (TrainingPlanActivity trainingPlanActivity : trainingPlan.getActivities()) {
			System.out.println("\t" + trainingPlanActivity.activity.getActivityName() + " on "
					+ trainingPlanActivity.weekDay + ";");
		}

		userDwarf.addTrainingPlan(trainingPlan);
		System.out.println("\nThe user '" + userDwarf.getName() + "' has a training plan that starts at "
				+ userDwarf.getTrainingPlans().get(0).getStartingDate() + ";");
	}

}
