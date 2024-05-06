package org.trivialfitness.state;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.trivialfitness.user.User;

public class AppState {

	private LocalDate now;

	private List<User> users;

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

}
