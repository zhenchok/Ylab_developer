package com.example.project;

import com.example.project.model.Role;
import com.example.project.model.Training;
import com.example.project.model.User;
import com.example.project.service.TrainingManagement;
import com.example.project.service.TrainingTypeManagement;
import com.example.project.service.UserManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static UserManagement userManagement = new UserManagement();
    private static User currentUser;
    private static final TrainingManagement trainingManagement = new TrainingManagement();
    private static final TrainingTypeManagement trainingTypeManagement = new TrainingTypeManagement();


    public static void main(String[] args) {
        userManagement = new UserManagement();
        scanner = new Scanner(System.in);
        userManagement.registerUser("zhenchok", "qwerty", Role.ADMIN);
        userManagement.registerUser("admin", "admin", Role.ADMIN);
        System.out.println("Добро пожаловать!");

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Зарегистрироваться");
            System.out.println("2. Войти");
            System.out.println("3. Выйти из программы");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    currentUser = login();
                    if (currentUser != null) {
                        chooseAction();
                    }
                    break;
                case 3:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Ошибка при парсинге даты и времени. Попробуйте снова.");
            return null;
        }
    }


    private static void registerUser() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine().trim();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();

        User user = userManagement.registerUser(username, password, Role.USER);
        if (user != null) {
            System.out.println("Пользователь успешно зарегистрирован.");
            currentUser = user;
        } else {
            System.out.println("Ошибка регистрации. Попробуйте снова.");
            registerUser();
        }
    }

    private static User login() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine().trim();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();

        User user = userManagement.authenticateUser(username, password);

        if (user != null) {
            System.out.println("Добро пожаловать, " + user.getUsername() + "!");
            return user;
        } else {
            System.out.println("Вы ввели неверное имя пользователя или пароль.");
            return null;
        }
    }

    private static void chooseAction() {
        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("Выберите действие:");

            if (currentUser.getRole() == Role.ADMIN) {
                System.out.println("1. Добавить нового пользователя");
                System.out.println("2. Удалить пользователя");
                System.out.println("3. Редактировать пользователя");
                System.out.println("4. Добавить новую тренировку");
                System.out.println("5. Просмотреть предыдущие тренировки");
                System.out.println("6. Список доступных тренировок");
                System.out.println("7. Просмотреть всех пользователей и их данные");
                System.out.println("8. Выйти из программы");
            } else {
                System.out.println("1. Добавить новую тренировку");
                System.out.println("2. Просмотреть предыдущие тренировки");
                System.out.println("3. Список доступных тренировок");
                System.out.println("4. Выйти из программы");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (currentUser.getRole() == Role.ADMIN) {
                        registerUser();
                    } else {
                        addNewTraining();
                    }
                    break;
                case 2:
                    if (currentUser.getRole() == Role.ADMIN) {
                        deleteUser();
                    } else {
                        viewPreviousTrainings();
                    }
                    break;
                case 3:
                    if (currentUser.getRole() == Role.ADMIN) {
                        editUser();
                    } else {
                        addTrainingFromList();

                    }
                    break;
                case 4:
                    if (currentUser.getRole() == Role.ADMIN) {
                        addNewTraining();
                    } else {
                        exitProgram = true;
                    }
                    break;
                case 5:
                    if (currentUser.getRole() == Role.ADMIN) {
                        viewPreviousTrainings();
                    } else {
                        System.out.println("Неверный выбор. Попробуйте еще раз.");
                    }
                    break;
                case 6:
                    if (currentUser.getRole() == Role.ADMIN) {
                        registerUser();
                    } else {
                        addTrainingFromList();
                    }
                    break;
                case 7:
                    if (currentUser.getRole() == Role.ADMIN) {
                        viewAllUsers();
                    } else {
                        System.out.println("Неверный выбор. Попробуйте еще раз.");
                    }
                    break;
                case 8:
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void deleteUser() {
        System.out.print("Введите имя пользователя для удаления: ");
        String usernameToDelete = scanner.nextLine().trim();

        boolean isDeleted = userManagement.deleteUser(currentUser, usernameToDelete);

        if (isDeleted) {
            System.out.println("Пользователь " + usernameToDelete + " успешно удален.");
        } else {
            System.out.println("Ошибка при удалении пользователя " + usernameToDelete + ". Попробуйте снова.");
        }
    }

    private static void editUser() {
        System.out.print("Введите имя пользователя для редактирования: ");
        String usernameToEdit = scanner.nextLine().trim();

        System.out.print("Введите новый пароль: ");
        String newPassword = scanner.nextLine().trim();

        System.out.print("Введите новую роль (USER или ADMIN): ");
        Role newRole = Role.valueOf(scanner.nextLine().trim().toUpperCase());

        boolean isEdited = userManagement.editUser(currentUser, usernameToEdit, newPassword, newRole);

        if (isEdited) {
            System.out.println("Пользователь " + usernameToEdit + " успешно отредактирован.");
        } else {
            System.out.println("Ошибка при редактировании пользователя " + usernameToEdit + ". Попробуйте снова.");
        }
    }

    private static void viewAllUsers() {
        Map<String, User> allUsers = userManagement.getAllUsers();
        for (Map.Entry<String, User> entry : allUsers.entrySet()) {
            System.out.println("Имя пользователя: " + entry.getKey());
            System.out.println("Данные пользователя: " + entry.getValue());
        }
    }


    private static void addNewTraining() {
        System.out.println("Введите дату тренировки в формате ДД.ММ.ГГГГ ЧЧ:мм :");
        String dateString = scanner.nextLine().trim();
        Date date = parseDate(dateString);

        System.out.println("Введите тип тренировки:");
        String type = scanner.nextLine().trim();

        System.out.println("Введите продолжительность тренировки в минутах:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите количество сжигаемых калорий за час:");
        int caloriesBurned = scanner.nextInt();
        scanner.nextLine();

        Training training = new Training(date, type, duration, caloriesBurned, "");
        boolean isAdded = trainingManagement.addTraining(currentUser.getUsername(), training);


        if (isAdded) {
            System.out.println("Тренировка успешно добавлена.");

            while (true) {
                System.out.println("Хотите добавить ещё тренировку? (yes/no)");
                String addType = scanner.nextLine().trim().toLowerCase();
                if (addType.equals("yes")) {
                    trainingTypeManagement.addNewTrainingType(type, "", caloriesBurned);
                    addNewTraining();
                    break;
                } else if (addType.equals("no")) {
                    break;
                } else {
                    System.out.println("Некорректный ответ. Попробуйте ещё раз.");
                }
            }
        } else {
            System.out.println("Ошибка при добавлении тренировки. Попробуйте снова.");
            addNewTraining();
        }
    }

    private static void viewPreviousTrainings() {
        List<Training> trainings = trainingManagement.getUserTrainings(currentUser.getUsername());


        if (trainings.isEmpty()) {
            System.out.println("У вас нет предыдущих тренировок.");
        } else {
            System.out.println("Ваши предыдущие тренировки:");
            for (Training training : trainings) {
                System.out.println(training);
            }
        }
    }

    private static void addTrainingFromList() {
        List<Training> availableTrainingTypes = trainingTypeManagement.getAllTrainingTypes();
        if (availableTrainingTypes.isEmpty()) {
            System.out.println("Список доступных тренировок пуст.");
            chooseAction();
            return;
        }
        System.out.println("Список доступных тренировок:");
        for (int i = 0; i < availableTrainingTypes.size(); i++) {
            Training training = availableTrainingTypes.get(i);
            System.out.println((i + 1) + ". Тип: " + training.getType() + ", Сжигаемые калории за час: " + training.getCaloriesBurned() + ", Дополнительная информация: " + training.getAdditionalInfo());
        }
        System.out.println("Выберите тренировку из списка (введите номер):");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > availableTrainingTypes.size()) {
            System.out.println("Неверный выбор. Попробуйте еще раз.");
            addTrainingFromList();
            return;
        }

        Training selectedTraining = availableTrainingTypes.get(choice - 1);
        System.out.println("Вы выбрали тренировку: " + selectedTraining.getType());
        System.out.println("Введите дату тренировки в формате ДД.ММ.ГГГГ ЧЧ:мм :");
        String dateString = scanner.nextLine().trim();
        Date date = parseDate(dateString);

        System.out.println("Введите продолжительность тренировки в минутах:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        Training training = new Training(date, selectedTraining.getType(), duration, selectedTraining.getCaloriesBurned(), selectedTraining.getAdditionalInfo());

        boolean isAdded = trainingManagement.addTraining(currentUser.getUsername(), training);

        if (isAdded) {
            System.out.println("Тренировка успешно добавлена.");


            System.out.println("Хотите добавить ещё тренировку? (yes/no)");
            String addType = scanner.nextLine().trim().toLowerCase();
            if (addType.equals("yes")) {

                addTrainingFromList();
            } else {
                chooseAction();
            }
        } else {
            System.out.println("Ошибка при добавлении тренировки. Попробуйте снова.");
            addTrainingFromList();
        }
    }


}
