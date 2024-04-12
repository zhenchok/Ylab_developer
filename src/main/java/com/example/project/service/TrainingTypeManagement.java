package com.example.project.service;

import com.example.project.model.Training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingTypeManagement {
    private List<Training> availableTrainingTypes;

    public TrainingTypeManagement() {
        this.availableTrainingTypes = new ArrayList<>();
        initializeTrainingTypes();
    }

    private void initializeTrainingTypes() {
        addTrainingType("Бег", "Среднее кардио, 10 минут", 100);
        addTrainingType("Плавание", "Интенсивное кардио, 20 минут", 200);
        addTrainingType("Велосипед", "Среднее кардио, 30 минут", 250);
        addTrainingType("Силовая тренировка", "Высокая интенсивность, 40 минут", 300);
        addTrainingType("Йога", "Средняя интенсивность, 45 минут", 150);
        addTrainingType("Пилатес", "Низкая интенсивность, 50 минут", 120);
        addTrainingType("Тренировка с гантелями", "Средняя интенсивность, 30 минут", 220);
        addTrainingType("Танцы", "Высокая интенсивность, 60 минут", 400);
        addTrainingType("Фитнес-бодибилдинг", "Высокая интенсивность, 40 минут", 350);
        addTrainingType("Кроссфит", "Очень высокая интенсивность, 45 минут", 500);
        addTrainingType("Аэробика", "Средняя интенсивность, 40 минут", 250);
        addTrainingType("Степ-аэробика", "Средняя интенсивность, 30 минут", 200);
        addTrainingType("Бокс", "Высокая интенсивность, 40 минут", 400);
        addTrainingType("Кардио-класс", "Средняя интенсивность, 50 минут", 300);
        addTrainingType("Тренировка на эллиптическом тренажере", "Средняя интенсивность, 40 минут", 280);
        addTrainingType("Барре", "Высокая интенсивность, 45 минут", 350);
        addTrainingType("Калланетика", "Средняя интенсивность, 30 минут", 200);
        addTrainingType("Тренировка на TRX", "Высокая интенсивность, 40 минут", 320);
        addTrainingType("Спортивная ходьба", "Средняя интенсивность, 50 минут", 230);
        addTrainingType("Растяжка", "Низкая интенсивность, 15 минут", 70);
    }

    private void addTrainingType(String name, String additionalInfo, int caloriesBurned) {
        Training trainingType = new Training(new Date(), name, 0, caloriesBurned, additionalInfo);
        availableTrainingTypes.add(trainingType);
    }

    public List<Training> getAllTrainingTypes() {
        return new ArrayList<>(availableTrainingTypes);
    }

    public void addNewTrainingType(String name, String additionalInfo, int caloriesBurned) {
        Training newTrainingType = new Training(new Date(), name, 0, caloriesBurned, additionalInfo);
        availableTrainingTypes.add(newTrainingType);
    }
}
