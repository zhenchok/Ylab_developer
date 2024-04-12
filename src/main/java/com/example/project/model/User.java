package com.example.project.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;

    private Role role;
    private List<Training> trainings;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.trainings = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void addTraining(Training training) {
        trainings.add(training);
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    @Override
    public String toString() {
        return "Пользователь: " + "Имя: " + username + " ; Пароль: " + password + " ; Роль: " + role;
    }
}
