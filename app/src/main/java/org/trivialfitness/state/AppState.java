package org.trivialfitness.state;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.trivialfitness.activity.*;
import org.trivialfitness.user.User;
import java.util.stream.Collectors;
import java.util.Arrays;

public class AppState implements Serializable {

	private LocalDate now;

	private List<User> users;

	private List<Activity> availableActivities = List.of(new BenchPress(0, 0), new MountainBike(0, 0), new PushUps(0),
			new Burpees(0), new Scissors(0), new Squats(0), new JumpingJacks(0), new Rowing(0));

	private List<List<String>> activityNamesByType = List.of(List.of("Rowing"), List.of("Mountain Bike"),
			List.of("Push Ups", "Burpees", "Scissors", "Squats", "Jumping Jacks"), List.of("Bench Press"));

	public AppState() {
		now = LocalDate.now();
		users = new ArrayList<>();
	}

	public AppState(AppState appState) {
		now = appState.now;
		users = appState.users.stream().map(User::copy).collect(Collectors.toList());

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
		return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().map(User::copy).orElse(null);
	}

	public List<String> getAvailableActivitiesTypesNames() {
		return availableActivities.stream().map(Activity::getActivityTypeName).distinct().toList();
	}

	public List<String> getAvailableActivitiesNames() {
		return availableActivities.stream().map(Activity::getActivityName).toList();
	}

	public List<String> getActivitiesFromSpecificType(int type) {
		return activityNamesByType.get(type);

	}

	public String getActivityDistanceName(int activity) {
		return activityNamesByType.get(0).get(activity);

	}

	public String getActivityDistanceAltimetryName(int activity) {
		return activityNamesByType.get(1).get(activity);

	}

	public String getActivityRepetitionName(int activity) {
		return activityNamesByType.get(2).get(activity);
	}

	public String getActivityRepetitionWeightName(int activity) {
		return activityNamesByType.get(3).get(activity);
	}

}
