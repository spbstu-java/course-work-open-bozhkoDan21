package com.example.coursework.lab4;

import com.example.coursework.lab4.exception.EmptyCollectionException;
import com.example.coursework.lab4.factory.ServiceFactory;
import com.example.coursework.lab4.service.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для демонстрации лабораторной работы 4 в GUI
 */
public class Lab4Demo {
    private final MathOperations mathService;
    private final StringOperations stringService;
    private final CollectionOperations collectionService;
    private StringBuilder output;

    public Lab4Demo() {
        this.mathService = ServiceFactory.createMathOperations();
        this.stringService = ServiceFactory.createStringOperations();
        this.collectionService = ServiceFactory.createCollectionOperations();
        this.output = new StringBuilder();
    }

    public String calculateAverage(String numbersInput) {
        output.setLength(0);
        try {
            List<Integer> numbers = parseIntegerList(numbersInput);
            double average = mathService.getAverage(numbers);
            output.append("📊 Среднее значение: ").append(average).append("\n")
                    .append("Числа: ").append(numbers).append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка: ").append(e.getMessage()).append("\n")
                    .append("Введите числа через пробел (например: 1 2 3 4)").append("\n");
        }
        return output.toString();
    }

    public String convertStringsWithPrefix(String stringsInput) {
        output.setLength(0);
        try {
            List<String> strings = parseStringList(stringsInput);
            List<String> result = stringService.convertToUpperCaseWithPrefix(strings);
            output.append("🔤 Строки с префиксом:\n")
                    .append("Исходные: ").append(strings).append("\n")
                    .append("Результат: ").append(result).append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка: ").append(e.getMessage()).append("\n")
                    .append("Введите строки через запятую (например: apple,banana,orange)").append("\n");
        }
        return output.toString();
    }

    public String getUniqueSquares(String numbersInput) {
        output.setLength(0);
        try {
            List<Integer> numbers = parseIntegerList(numbersInput);
            List<Integer> result = mathService.getUniqueElementsSquared(numbers);
            output.append("🔢 Квадраты уникальных элементов:\n")
                    .append("Исходные: ").append(numbers).append("\n")
                    .append("Результат: ").append(result).append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка: ").append(e.getMessage()).append("\n")
                    .append("Введите числа через пробел (например: 1 2 2 3 4 4 5)").append("\n");
        }
        return output.toString();
    }

    public String getLastElement(String collectionInput) {
        output.setLength(0);
        try {
            List<String> collection = parseStringList(collectionInput);
            String result = collectionService.getLastElement(collection);
            output.append("📦 Последний элемент коллекции:\n")
                    .append("Коллекция: ").append(collection).append("\n")
                    .append("Последний элемент: '").append(result).append("'\n");
        } catch (EmptyCollectionException e) {
            output.append("❌ Коллекция пуста: ").append(e.getMessage()).append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка: ").append(e.getMessage()).append("\n")
                    .append("Введите элементы через запятую (например: a,b,c,d)").append("\n");
        }
        return output.toString();
    }

    public String sumEvenNumbers(String numbersInput) {
        output.setLength(0);
        try {
            List<Integer> numbersList = parseIntegerList(numbersInput);
            int[] numbers = numbersList.stream().mapToInt(Integer::intValue).toArray();
            int result = mathService.sumOfEvenNumbers(numbers);
            output.append("➕ Сумма четных чисел:\n")
                    .append("Числа: ").append(numbersList).append("\n")
                    .append("Сумма четных: ").append(result).append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка: ").append(e.getMessage()).append("\n")
                    .append("Введите числа через пробел (например: 1 2 3 4 5 6)").append("\n");
        }
        return output.toString();
    }

    public String convertToMapByFirstChar(String stringsInput) {
        output.setLength(0);
        try {
            List<String> strings = parseStringList(stringsInput);
            Map<Character, String> result = stringService.getMapByFirstCharacter(strings);
            output.append("🗺️ Map по первому символу:\n")
                    .append("Строки: ").append(strings).append("\n")
                    .append("Результат: ").append(result).append("\n");
        } catch (Exception e) {
            output.append("❌ Ошибка: ").append(e.getMessage()).append("\n")
                    .append("Введите строки через запятую (например: apple,banana,avocado)").append("\n");
        }
        return output.toString();
    }

    public String getOperationsInfo() {
        output.setLength(0);
        output.append("📋 Доступные операции Stream API:\n")
                .append("1. Среднее значение списка чисел\n")
                .append("2. Строки в верхний регистр с префиксом '_new_'\n")
                .append("3. Квадраты уникальных элементов\n")
                .append("4. Последний элемент коллекции\n")
                .append("5. Сумма четных чисел\n")
                .append("6. Map по первому символу строк\n")
                .append("\n💡 Подсказки:\n")
                .append("- Числа вводите через пробел: 1 2 3 4\n")
                .append("- Строки вводите через запятую: apple,banana,orange\n");
        return output.toString();
    }

    private List<Integer> parseIntegerList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(input.trim().split("\\s+"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> parseStringList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}