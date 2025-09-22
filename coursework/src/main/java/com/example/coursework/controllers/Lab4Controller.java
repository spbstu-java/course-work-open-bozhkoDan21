package com.example.coursework.controllers;

import com.example.coursework.lab4.Lab4Demo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Lab4Controller {

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField numbersInputField;

    @FXML
    private TextField stringsInputField;

    @FXML
    private Button averageButton;

    @FXML
    private Button prefixButton;

    @FXML
    private Button uniqueSquaresButton;

    @FXML
    private Button lastElementButton;

    @FXML
    private Button sumEvenButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button clearButton;

    private Lab4Demo lab4Demo;

    @FXML
    private void initialize() {
        lab4Demo = new Lab4Demo();
        outputTextArea.setEditable(false);
        outputTextArea.setText("Добро пожаловать в Lab4 - Stream API операции!\n");

        // Настройка обработчиков кнопок
        averageButton.setOnAction(event -> calculateAverage());
        prefixButton.setOnAction(event -> convertStringsWithPrefix());
        uniqueSquaresButton.setOnAction(event -> getUniqueSquares());
        lastElementButton.setOnAction(event -> getLastElement());
        sumEvenButton.setOnAction(event -> sumEvenNumbers());
        mapButton.setOnAction(event -> convertToMapByFirstChar());
        infoButton.setOnAction(event -> showOperationsInfo());
        clearButton.setOnAction(event -> clearInputs());
    }

    private void calculateAverage() {
        String input = numbersInputField.getText().trim();
        String result = lab4Demo.calculateAverage(input);
        outputTextArea.setText(result);
    }

    private void convertStringsWithPrefix() {
        String input = stringsInputField.getText().trim();
        String result = lab4Demo.convertStringsWithPrefix(input);
        outputTextArea.setText(result);
    }

    private void getUniqueSquares() {
        String input = numbersInputField.getText().trim();
        String result = lab4Demo.getUniqueSquares(input);
        outputTextArea.setText(result);
    }

    private void getLastElement() {
        String input = stringsInputField.getText().trim();
        String result = lab4Demo.getLastElement(input);
        outputTextArea.setText(result);
    }

    private void sumEvenNumbers() {
        String input = numbersInputField.getText().trim();
        String result = lab4Demo.sumEvenNumbers(input);
        outputTextArea.setText(result);
    }

    private void convertToMapByFirstChar() {
        String input = stringsInputField.getText().trim();
        String result = lab4Demo.convertToMapByFirstChar(input);
        outputTextArea.setText(result);
    }

    private void showOperationsInfo() {
        String result = lab4Demo.getOperationsInfo();
        outputTextArea.setText(result);
    }

    private void clearInputs() {
        numbersInputField.clear();
        stringsInputField.clear();
        outputTextArea.setText("Поля очищены. Введите данные для операций.\n");
    }
}