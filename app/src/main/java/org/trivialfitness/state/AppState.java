package org.trivialfitness.state;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import org.trivialfitness.user.*;

import org.trivialfitness.activity.activityType.*;
import org.trivialfitness.trainingPlan.TrainingPlan;

import java.util.stream.Collectors;
import org.trivialfitness.activity.*;

import java.util.function.BiFunction;

public class AppState implements Serializable {

	private LocalDate now;

	private List<User> users;

	private static List<Activity> availableActivities = List.of(new BenchPress(0, 0), new MountainBike(0, 0),
			new PushUps(0),

			new Burpees(0), new Scissors(0), new Squats(0), new JumpingJacks(0), new Rowing(0), new BenchPress(0, 0),
			new Climbing(0, 0), new Deadlift(0, 0), new IndoorCycling(0), new JumpRope(0), new OutdoorCycling(0, 0),
			new Running(0), new Scissors(0), new Sprint(0), new Squats(0), new Surfing(0), new Swimming(0),
			new Weightlifting(0, 0));

	private static List<List<String>> activityNamesByType = List.of(
			/* Distance */
			List.of("Rowing", "Running", "Swimming", "Surfing", "Indoor Cycling", "Walking"),
			/* Distance/Altimetry */
			List.of("Mountain Bike", "Outdoor Cycling", "Climbing"),
			/* Repetitions */
			List.of("Push Ups", "Burpees", "Scissors", "Squats", "Jumping Jacks", "Jump Rope", "Sprint"),
			/* Repetitions/Weight */
			List.of("Bench Press", "Deadlift", "Weightlifting"));

	public AppState() {
		try {
			loadProgress();
		}
		catch (Exception e) {
			now = LocalDate.now();
			users = new ArrayList<>();
		}
	}

	public Map<String, BiFunction<Integer, Integer, Activity>> initializeActivityCreators() {
		Map<String, BiFunction<Integer, Integer, Activity>> activityCreators = new HashMap<>();
		/* Distance */
		activityCreators.put("Rowing", (distance, something) -> new Rowing(distance));
		activityCreators.put("Indoor Cycling", (distance, something) -> new IndoorCycling(distance));
		activityCreators.put("Running", (distance, something) -> new Running(distance));
		activityCreators.put("Walking", (distance, something) -> new Walking(distance));
		activityCreators.put("Surfing", (distance, something) -> new Surfing(distance));
		activityCreators.put("Swimming", (distance, something) -> new Swimming(distance));
		/* Distance/Altimetry */
		activityCreators.put("Mountain Bike", (distance, altimetry) -> new MountainBike(distance, altimetry));
		activityCreators.put("Outdoor Cycling", (distance, altimetry) -> new OutdoorCycling(distance, altimetry));
		activityCreators.put("Climbing", (distance, altimetry) -> new Climbing(distance, altimetry));
		/* Repetitions */
		activityCreators.put("Push Ups", (repetitions, something) -> new PushUps(repetitions));
		activityCreators.put("Burpees", (repetitions, something) -> new Burpees(repetitions));
		activityCreators.put("Scissors", (repetitions, something) -> new Scissors(repetitions));
		activityCreators.put("Squats", (repetitions, something) -> new Squats(repetitions));
		activityCreators.put("Jump Rope", (repetitions, something) -> new JumpRope(repetitions));
		activityCreators.put("Jumping Jacks", (repetitions, something) -> new JumpingJacks(repetitions));
		activityCreators.put("Sprint", (repetitions, something) -> new Sprint(repetitions));
		/* Repetitions/Weight */
		activityCreators.put("Bench Press", (repetitions, weight) -> new BenchPress(repetitions, weight));
		activityCreators.put("Deadlift", (repetitions, weight) -> new Deadlift(repetitions, weight));
		activityCreators.put("Weightlifting", (repetitions, weight) -> new Weightlifting(repetitions, weight));

		return activityCreators;
	}

	public LocalDate getCurrentDate() {
		return now;
	}

	public void advanceDays(int days) {
		LocalDate startingDate = now;
		now = now.plusDays(days);

		users.forEach(user -> user.updateUserOnTimeChange(startingDate, now));
	}

	public boolean addUser(User user) {
		if (users.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()))) {
			return false;
		}
		else {
			users.add(user);
			return true;
		}
	}

	public List<User> getUsers() {
		return users.stream().map(User::copy).toList();
	}

	public User getUser(String userId) {
		return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
	}

	public static List<Activity> getAvailableActivities() {
		return availableActivities.stream().map(Activity::copy).toList();
	}

	public static List<String> getAvailableActivitiesTypesNames() {
		return availableActivities.stream().map(Activity::getActivityTypeName).distinct().toList();
	}

	public static List<String> getAvailableActivitiesNames() {
		return availableActivities.stream().map(Activity::getActivityName).toList();
	}

	public static List<String> getActivitiesFromSpecificType(int type) {
		return activityNamesByType.get(type);

	}

	public static String getActivityDistanceName(int activity) {
		return activityNamesByType.get(0).get(activity);

	}

	public static String getActivityDistanceAltimetryName(int activity) {
		return activityNamesByType.get(1).get(activity);

	}

	public static String getActivityRepetitionName(int activity) {
		return activityNamesByType.get(2).get(activity);
	}

	public static String getActivityRepetitionWeightName(int activity) {
		return activityNamesByType.get(3).get(activity);
	}

	public BiFunction<Integer, Integer, Activity> getActivityCreator(String activityName) {
		Map<String, BiFunction<Integer, Integer, Activity>> activityCreators = initializeActivityCreators();
		return activityCreators.get(activityName);
	}

	public TrainingPlan getNewTrainingPlan(LocalDate startingDate, LocalDate endingDate) {
		return new TrainingPlan(startingDate, endingDate);
	}

	public String saveProgress() {
		try {
			FileOutputStream fileOut = new FileOutputStream("appState.bin");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.now);
			out.writeObject(this.users);
			out.close();
			fileOut.close();
			return "Progress saved.";
		}
		catch (IOException i) {
			i.printStackTrace();
			return "Error saving progress.";
		}
	}

	public void loadProgress() {
		try {
			FileInputStream fileIn = new FileInputStream("appState.bin");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.now = (LocalDate) in.readObject();
			this.users = readUsers(in);
			in.close();
			fileIn.close();
			return;
		}
		catch (java.io.FileNotFoundException e) {
			this.now = LocalDate.now();
			this.users = new ArrayList<>();
			return;
		}
		catch (IOException i) {
			i.printStackTrace();
			return;
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}

	}

	private List<User> readUsers(ObjectInputStream in) throws IOException {
		try {
			Object obj = in.readObject();
			if (obj instanceof List<?>) {
				List<?> lista = (List<?>) obj;

				List<User> users = new ArrayList<>();
				for (Object item : lista) {
					if (item instanceof User) {
						users.add((User) item);
					}
				}
				return users;
			}
			else {
				throw new IOException("Invalid data format: expected a list of users");
			}
		}
		catch (ClassNotFoundException e) {
			throw new IOException("Failed to read users: class not found", e);
		}
	}

}
