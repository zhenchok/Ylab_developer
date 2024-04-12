package com.example.project.model;

import java.util.Date;
import java.util.Objects;

public class Training {
    private final Date date;
    private final String type;
    private final int durationInMinutes;
    private final int caloriesBurned;
    private final String additionalInfo;

    public Training(Date date, String type, int durationInMinutes, int caloriesBurned, String additionalInfo) {
        this.date = date;
        this.type = type;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = caloriesBurned;
        this.additionalInfo = additionalInfo;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return durationInMinutes == training.durationInMinutes &&
                caloriesBurned == training.caloriesBurned &&
                Objects.equals(date, training.date) &&
                Objects.equals(type, training.type) &&
                Objects.equals(additionalInfo, training.additionalInfo);
    }

    public int hashCode() {
        return Objects.hash(date, type, durationInMinutes, caloriesBurned, additionalInfo);
    }

    public String toString() {
        return "Тренировка: Дата : " + date + " ; Тип : " + type + " ; Продолжительность тренировки (мин) : " + durationInMinutes + " ; Сжигаемые калории за час : " + caloriesBurned + " ; Дополнительная информация : " + additionalInfo;
    }
}
