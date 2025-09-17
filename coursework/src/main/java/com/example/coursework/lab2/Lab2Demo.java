package com.example.coursework.lab2;

import com.example.coursework.lab2.domain.SampleService;
import com.example.coursework.lab2.processor.MethodInvoker;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Класс для демонстрации лабораторной работы 2 в GUI
 */
public class Lab2Demo {
    private SampleService sampleService;
    private MethodInvoker methodInvoker;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    public Lab2Demo() {
        this.sampleService = new SampleService();
        this.methodInvoker = new MethodInvoker();
        this.outputStream = new ByteArrayOutputStream();
        this.originalOut = System.out;
    }

    public String invokePublicMethods() {
        System.setOut(new PrintStream(outputStream));
        outputStream.reset();

        System.out.println("=== Вызов PUBLIC методов ===");
        methodInvoker.invokePublicMethods(sampleService);
        System.out.println("Готово!");

        System.setOut(originalOut);
        return outputStream.toString();
    }

    public String invokePrivateMethods() {
        System.setOut(new PrintStream(outputStream));
        outputStream.reset();

        System.out.println("=== Вызов PRIVATE методов ===");
        methodInvoker.invokePrivateMethods(sampleService);
        System.out.println("Готово!");

        System.setOut(originalOut);
        return outputStream.toString();
    }

    public String invokeProtectedMethods() {
        System.setOut(new PrintStream(outputStream));
        outputStream.reset();

        System.out.println("=== Вызов PROTECTED методов ===");
        methodInvoker.invokeProtectedMethods(sampleService);
        System.out.println("Готово!");

        System.setOut(originalOut);
        return outputStream.toString();
    }

    public String invokeAllMethods() {
        System.setOut(new PrintStream(outputStream));
        outputStream.reset();

        System.out.println("=== Вызов ВСЕХ методов ===");
        methodInvoker.invokeAllMethods(sampleService);
        System.out.println("Готово!");

        System.setOut(originalOut);
        return outputStream.toString();
    }

    public String getMethodInfo() {
        return "=== ИНФОРМАЦИЯ О МЕТОДАХ ===\n" +
                "Класс: SampleService\n" +
                "Аннотированные методы:\n" +
                "- publicMethod() - 2 вызова\n" +
                "- publicMethodWithParams(String, int) - 2 вызова\n" +
                "- privateMethod() - 3 вызова\n" +
                "- privateMethodWithParams(String, int) - 3 вызова\n" +
                "- protectedMethod() - 4 вызова\n" +
                "- protectedMethodWithParams(String, int) - 4 вызова\n";
    }

    public void resetOutput() {
        outputStream.reset();
    }
}