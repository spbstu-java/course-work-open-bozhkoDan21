package com.example.coursework.controllers;

import com.example.coursework.lab1.Lab1Demo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Lab1Controller {

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button walkButton;

    @FXML
    private Button rideButton;

    @FXML
    private Button flyButton;

    @FXML
    private Button moveButton;

    @FXML
    private Button positionButton;

    @FXML
    private Button strategyButton;

    @FXML
    private Button resetButton;

    private Lab1Demo lab1Demo;

    @FXML
    private void initialize() {
        lab1Demo = new Lab1Demo();
        outputTextArea.setEditable(false);
        outputTextArea.setText("Добро пожаловать в лабораторную работу 1!\nСоздан новый герой.\n");

        // Эффекты наведения для кнопок
        setupHoverEffect(walkButton, "#c19a6b");
        setupHoverEffect(rideButton, "#b8860b");
        setupHoverEffect(flyButton, "#8b4513");
        setupHoverEffect(moveButton, "#556b2f");
        setupHoverEffect(positionButton, "#5f9ea0");
        setupHoverEffect(strategyButton, "#7b68ee");
        setupHoverEffect(resetButton, "#b22222");

        // Обработчики кнопок
        walkButton.setOnAction(event -> setWalkStrategy());
        rideButton.setOnAction(event -> setRideHorseStrategy());
        flyButton.setOnAction(event -> setFlyStrategy());
        moveButton.setOnAction(event -> moveHero());
        positionButton.setOnAction(event -> showPosition());
        strategyButton.setOnAction(event -> showStrategy());
        resetButton.setOnAction(event -> resetDemo());
    }

    private void setupHoverEffect(Button button, String hoverColor) {
        String originalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            button.setStyle(originalStyle + "-fx-background-color: " + hoverColor + ";");
        });

        button.setOnMouseExited(e -> {
            button.setStyle(originalStyle);
        });
    }

    private void setWalkStrategy() {
        lab1Demo.setWalkStrategy();
        appendOutput("Установлена стратегия: Пешком\n");
    }

    private void setRideHorseStrategy() {
        lab1Demo.setRideHorseStrategy();
        appendOutput("Установлена стратегия: На лошади\n");
    }

    private void setFlyStrategy() {
        lab1Demo.setFlyStrategy();
        appendOutput("Установлена стратегия: Полёт\n");
    }

    private void moveHero() {
        String result = lab1Demo.move();
        appendOutput(result + "\n");
    }

    private void showPosition() {
        String position = lab1Demo.getCurrentPosition();
        appendOutput(position + "\n");
    }

    private void showStrategy() {
        String strategy = lab1Demo.getCurrentStrategy();
        appendOutput(strategy + "\n");
    }

    private void resetDemo() {
        lab1Demo.reset();
        outputTextArea.clear();
        appendOutput("Демонстрация сброшена. Создан новый герой.\n");
    }

    private void appendOutput(String text) {
        outputTextArea.appendText(text);
    }
}