package com.example.project.service;

import com.example.project.model.Training;

import java.util.*;
import java.util.stream.Collectors;

public class TrainingManagement {
    private Map<String, List<Training>> userTrainings;

    public TrainingManagement() {
        this.userTrainings = new HashMap<>();
    }

    public boolean addTraining(String username, Training training) {
        if (username == null || training == null) {
            return false;
        }
        userTrainings.putIfAbsent(username, new ArrayList<>());
        return userTrainings.get(username).add(training);
    }

    public List<Training> getUserTrainings(String username) {
        return userTrainings.getOrDefault(username, new ArrayList<>());
    }

    public boolean editTraining(String username, int trainingIndex, Training editedTraining) {
        List<Training> trainings = userTrainings.get(username);
        if (username == null || trainings == null || trainingIndex < 0 || trainingIndex >= trainings.size()) {
            return false;
        }
        trainings.set(trainingIndex, editedTraining);
        return true;
    }

    public boolean deleteTraining(String username, int trainingIndex) {
        List<Training> trainings = userTrainings.get(username);
        if (username == null || trainings == null || trainingIndex < 0 || trainingIndex >= trainings.size()) {
            return false;
        }
        trainings.remove(trainingIndex);
        return true;
    }

    public List<Training> getUserTrainingsSortedByDate(String username) {
        List<Training> trainings = userTrainings.getOrDefault(username, new ArrayList<>());

        // Сортировка списка тренировок по дате
        List<Training> sortedTrainings = trainings.stream()
                .sorted(Comparator.comparing(Training::getDate))
                .collect(Collectors.toList());

        return sortedTrainings;
    }

    public int getTotalCaloriesBurned(String username) {
        List<Training> trainings = userTrainings.getOrDefault(username, new ArrayList<>());
        int totalCaloriesBurned = 0;
        for (Training training : trainings) {
            totalCaloriesBurned += training.getCaloriesBurned();
        }
        return totalCaloriesBurned;
    }

    public double getAverageCaloriesBurned(String username) {
        List<Training> trainings = userTrainings.getOrDefault(username, new ArrayList<>());
        int totalCaloriesBurned = getTotalCaloriesBurned(username);
        if (trainings.isEmpty()) {
            return 0.0;
        }
        return (double) totalCaloriesBurned / trainings.size();
    }

    public int getTotalDuration(String username) {
        List<Training> trainings = userTrainings.getOrDefault(username, new ArrayList<>());
        int totalDuration = 0;
        for (Training training : trainings) {
            totalDuration += training.getDurationInMinutes();
        }
        return totalDuration;
    }
}
