package com.example.project.service;

import com.example.project.model.Role;
import com.example.project.model.Training;
import com.example.project.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagement {
    private Map<String, User> users;
    private List<Training> trainings;

    public UserManagement() {
        this.users = new HashMap<>();
        this.trainings = new ArrayList<>();
    }

    public User registerUser(String username, String password, Role role) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }
        if (users.containsKey(username)) {
            return null;
        }

        User newUser = new User(username, password, role);
        users.put(username, newUser);
        return newUser;
    }

    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean deleteUser(User currentUser, String usernameToDelete) {
        if (users.containsKey(usernameToDelete)) {
            users.remove(usernameToDelete);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }

    public boolean editUser(User currentUser, String usernameToEdit, String newPassword, Role newRole) {
        User user = users.get(usernameToEdit);
        if (user != null) {
            user.setPassword(newPassword);
            user.setRole(newRole);
            return true;
        } else {
            return false;
        }
    }

    public boolean addTraining(User currentUser, String username, Training training) {
        if (users.containsKey(username)) {
            users.get(username).addTraining(training);
            return true;
        } else {
            return false;
        }
    }

    public List<Training> getUserTrainings(User currentUser, String username) {
        if (users.containsKey(username)) {
            return users.get(username).getTrainings();
        } else {
            return null;
        }
    }

    public void addTraining(Training training) {
        trainings.add(training);
    }

    public List<Training> getTrainings() {
        return trainings;
    }
}
