package org.trivialfitness.state;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.trivialfitness.activity.*;
import org.trivialfitness.user.User;
import java.util.stream.Collectors;

public class AppState implements Serializable {

	private LocalDate now;

	private List<User> users;

	private List<Activity> availableActivities = List.of(new BenchPress(0, 0), new MountainBike(0, 0), new PushUps(0),
			new Rowing(0));

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

}
