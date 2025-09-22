package com.example.coursework.controllers;

import com.example.coursework.lab2.Lab2Demo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Lab2Controller {

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button publicMethodsButton;

    @FXML
    private Button privateMethodsButton;

    @FXML
    private Button protectedMethodsButton;

    @FXML
    private Button allMethodsButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button resetButton;

    private Lab2Demo lab2Demo;

    @FXML
    private void initialize() {
        lab2Demo = new Lab2Demo();
        outputTextArea.setEditable(false);
        outputTextArea.setText("Добро пожаловать в лабораторную работу 2!\nВыберите действие.\n");

        // Настройка обработчиков кнопок
        publicMethodsButton.setOnAction(event -> invokePublicMethods());
        privateMethodsButton.setOnAction(event -> invokePrivateMethods());
        protectedMethodsButton.setOnAction(event -> invokeProtectedMethods());
        allMethodsButton.setOnAction(event -> invokeAllMethods());
        infoButton.setOnAction(event -> showMethodInfo());
        resetButton.setOnAction(event -> resetOutput());
    }

    private void invokePublicMethods() {
        String result = lab2Demo.invokePublicMethods();
        outputTextArea.setText(result);
    }

    private void invokePrivateMethods() {
        String result = lab2Demo.invokePrivateMethods();
        outputTextArea.setText(result);
    }

    private void invokeProtectedMethods() {
        String result = lab2Demo.invokeProtectedMethods();
        outputTextArea.setText(result);
    }

    private void invokeAllMethods() {
        String result = lab2Demo.invokeAllMethods();
        outputTextArea.setText(result);
    }

    private void showMethodInfo() {
        String info = lab2Demo.getMethodInfo();
        outputTextArea.setText(info);
    }

    private void resetOutput() {
        lab2Demo.resetOutput();
        outputTextArea.setText("Вывод очищен. Выберите действие.\n");
    }
}