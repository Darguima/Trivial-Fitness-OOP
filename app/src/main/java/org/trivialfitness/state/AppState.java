package org.trivialfitness.state;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.trivialfitness.activity.*;
import org.trivialfitness.user.User;

public class AppState implements Serializable {

	private LocalDate now;

	private List<User> users;

	private List<Activity> availableActivities = List.of(new BenchPress(0, 0), new MountainBike(0, 0), new PushUps(0),
			new Burpees(0), new Scissors(0), new Squats(0), new JumpingJacks(0), new Rowing(0));

	public AppState() {
		now = LocalDate.now();
		users = new ArrayList<>();
	}

	public LocalDate getCurrentDate() {
		return now;
	}

	public void advanceDays(int days) {
		LocalDate startingDate = now;
		now = now.plusDays(days);

		users.forEach(user -> user.updateUserOnTimeChange(startingDate, now));
	}

	public void addUser(User user) {
		users.add(user.copy());
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
