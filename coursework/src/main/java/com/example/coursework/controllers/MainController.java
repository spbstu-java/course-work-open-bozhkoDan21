package com.example.coursework.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button lab1Button;

    @FXML
    private Button lab2Button;

    @FXML
    private Button lab3Button;

    @FXML
    private Button lab4Button;

    @FXML
    private void initialize() {
        // Стили для кнопок при наведении
        setupButtonHoverEffects(lab1Button, "#c19a6b");
        setupButtonHoverEffects(lab2Button, "#c19a6b");
        setupButtonHoverEffects(lab3Button, "#c19a6b");
        setupButtonHoverEffects(lab4Button, "#c19a6b");

        lab1Button.setOnAction(event -> openLabWindow("lab1-view.fxml", "Лабораторная работа 1"));
        lab2Button.setOnAction(event -> openLabWindow("lab2-view.fxml", "Лабораторная работа 2"));
        lab3Button.setOnAction(event -> openLabWindow("lab3-view.fxml", "Лабораторная работа 3"));
        lab4Button.setOnAction(event -> openLabWindow("lab4-view.fxml", "Лабораторная работа 4"));
    }

    private void setupButtonHoverEffects(Button button, String hoverColor) {
        String originalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            button.setStyle(originalStyle + "-fx-background-color: " + hoverColor + ";");
        });

        button.setOnMouseExited(e -> {
            button.setStyle(originalStyle);
        });
    }

    private void openLabWindow(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/coursework/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Ошибка загрузки окна: " + e.getMessage());
        }
    }

    private void showNotImplementedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("В разработке");
        alert.setHeaderText("Функциональность в разработке");
        alert.setContentText("Данная лабораторная работа будет реализована позже.");
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Произошла ошибка");
        alert.setContentText(message);
        alert.showAndWait();
    }
}