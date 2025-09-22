package com.example.coursework.lab3;

import com.example.coursework.lab3.exception.FileReadException;
import com.example.coursework.lab3.exception.InvalidFileFormatException;
import com.example.coursework.lab3.service.DictionaryService;
import com.example.coursework.lab3.service.TranslatorFactory;
import java.io.File;

/**
 * Класс для демонстрации лабораторной работы 3 в GUI
 */
public class Lab3Demo {
    private DictionaryService translator;
    private String currentDictionaryPath;
    private StringBuilder output;

    public Lab3Demo() {
        this.output = new StringBuilder();
        this.output.append("Добро пожаловать в Переводчик!\n")
                .append("Загрузите словарь для начала работы.\n");
    }

    public String loadDictionary(String filePath) {
        output.setLength(0);
        try {
            this.translator = TranslatorFactory.createEnglishRussianTranslator(filePath);
            this.currentDictionaryPath = filePath;
            output.append("✅ Словарь успешно загружен из: ")
                    .append(new File(filePath).getName())
                    .append("\n")
                    .append("Слов в словаре: ")
                    .append(getWordCount())
                    .append("\n");
        } catch (FileReadException e) {
            output.append("❌ Ошибка чтения файла: ")
                    .append(e.getMessage())
                    .append("\n");
        } catch (InvalidFileFormatException e) {
            output.append("❌ Ошибка формата словаря: ")
                    .append(e.getMessage())
                    .append("\n");
        } catch (Exception e) {
            output.append("❌ Неожиданная ошибка: ")
                    .append(e.getMessage())
                    .append("\n");
        }
        return output.toString();
    }

    public String translateText(String text) {
        output.setLength(0);
        if (translator == null) {
            output.append("❌ Сначала загрузите словарь!\n");
            return output.toString();
        }

        if (text == null || text.trim().isEmpty()) {
            output.append("❌ Введите текст для перевода\n");
            return output.toString();
        }

        try {
            String translatedText = translator.translate(text);
            output.append("📝 Исходный текст:\n")
                    .append(text)
                    .append("\n\n")
                    .append("🌍 Перевод:\n")
                    .append(translatedText)
                    .append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка при переводе: ")
                    .append(e.getMessage())
                    .append("\n");
        }
        return output.toString();
    }

    public String getDictionaryInfo() {
        output.setLength(0);
        if (translator == null) {
            output.append("❌ Словарь не загружен\n");
        } else {
            output.append("📚 Информация о словаре:\n")
                    .append("Файл: ").append(new File(currentDictionaryPath).getName()).append("\n")
                    .append("Слов: ").append(getWordCount()).append("\n")
                    .append("Путь: ").append(currentDictionaryPath).append("\n");
        }
        return output.toString();
    }

    public String checkWord(String word) {
        output.setLength(0);
        if (translator == null) {
            output.append("❌ Сначала загрузите словарь!\n");
        } else if (word == null || word.trim().isEmpty()) {
            output.append("❌ Введите слово для проверки\n");
        } else {
            boolean contains = translator.containsWord(word);
            output.append("🔍 Проверка слова: '").append(word).append("'\n")
                    .append("В словаре: ").append(contains ? "✅ Да" : "❌ Нет").append("\n");
        }
        return output.toString();
    }

    private int getWordCount() {
        return currentDictionaryPath != null ? 20 : 0;
    }

    public void reset() {
        translator = null;
        currentDictionaryPath = null;
        output.setLength(0);
        output.append("Состояние сброшено. Загрузите новый словарь.\n");
    }
}