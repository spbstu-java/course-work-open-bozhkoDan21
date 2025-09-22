package com.example.coursework.controllers;

import com.example.coursework.lab3.Lab3Demo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class Lab3Controller {

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField dictionaryPathField;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField checkWordField;

    @FXML
    private Button browseButton;

    @FXML
    private Button loadDictionaryButton;

    @FXML
    private Button translateButton;

    @FXML
    private Button checkWordButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button autoFindButton;

    private Lab3Demo lab3Demo;
    private Stage stage;

    @FXML
    private void initialize() {
        lab3Demo = new Lab3Demo();
        outputTextArea.setEditable(false);
        outputTextArea.setText(lab3Demo.getDictionaryInfo());

        // Настройка обработчиков кнопок
        browseButton.setOnAction(event -> browseForDictionary());
        loadDictionaryButton.setOnAction(event -> loadDictionary());
        translateButton.setOnAction(event -> translateText());
        checkWordButton.setOnAction(event -> checkWord());
        infoButton.setOnAction(event -> showDictionaryInfo());
        resetButton.setOnAction(event -> resetTranslator());
        autoFindButton.setOnAction(event -> autoFindDictionary());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void browseForDictionary() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл словаря");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt")
        );

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            dictionaryPathField.setText(file.getAbsolutePath());
        }
    }

    private void autoFindDictionary() {
        // Текущая директория проекта
        String projectDir = System.getProperty("user.dir");

        // Правильные пути для поиска
        String[] possiblePaths = {
                projectDir + "/src/main/resources/com/example/coursework/dictionary.txt", // Правильный путь
                projectDir + "/src/main/resources/dictionary.txt",
                projectDir + "/resources/dictionary.txt",
                "src/main/resources/com/example/coursework/dictionary.txt",
                "src/main/resources/dictionary.txt",
                "resources/dictionary.txt",
                "dictionary.txt"
        };

        outputTextArea.setText("🔍 Поиск dictionary.txt...\n");

        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                dictionaryPathField.setText(file.getAbsolutePath());
                outputTextArea.appendText("✅ Найден: " + file.getAbsolutePath() +
                        "\nНажмите 'Загрузить' для загрузки словаря.");
                return;
            }
            outputTextArea.appendText("Проверен: " + path + " - ❌\n");
        }

        outputTextArea.appendText("\n❌ Файл dictionary.txt не найден.\n" +
                "Разместите его в: src/main/resources/com/example/coursework/");
    }

    private void loadDictionary() {
        String filePath = dictionaryPathField.getText().trim();
        if (filePath.isEmpty()) {
            outputTextArea.setText("❌ Введите путь к файлу словаря\n");
            return;
        }

        String result = lab3Demo.loadDictionary(filePath);
        outputTextArea.setText(result);
    }

    private void translateText() {
        String text = inputTextField.getText().trim();
        String result = lab3Demo.translateText(text);
        outputTextArea.setText(result);
    }

    private void checkWord() {
        String word = checkWordField.getText().trim();
        String result = lab3Demo.checkWord(word);
        outputTextArea.setText(result);
    }

    private void showDictionaryInfo() {
        String result = lab3Demo.getDictionaryInfo();
        outputTextArea.setText(result);
    }

    private void resetTranslator() {
        lab3Demo.reset();
        dictionaryPathField.clear();
        inputTextField.clear();
        checkWordField.clear();
        outputTextArea.setText("Состояние сброшено. Загрузите новый словарь.\n");
    }
}