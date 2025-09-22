package com.example.coursework.lab2.processor;

import com.example.coursework.lab2.annotation.InvokeMultiple;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MethodInvoker {

    private static final Map<Class<?>, Object> DEFAULT_VALUES = new HashMap<>();

    static {
        DEFAULT_VALUES.put(String.class, "DEFAULT_USER");
        DEFAULT_VALUES.put(int.class, 25);
        DEFAULT_VALUES.put(Integer.class, 25);
        DEFAULT_VALUES.put(double.class, 25.0);
        DEFAULT_VALUES.put(Double.class, 25.0);
    }

    /**
     * Вызывает все аннотированные методы
     */
    public void invokeAllMethods(Object target) {
        invokeAnnotatedMethodsByModifier(target, 0);
    }

    /**
     * Вызывает только public методы
     */
    public void invokePublicMethods(Object target) {
        invokeAnnotatedMethodsByModifier(target, Modifier.PUBLIC);
    }

    /**
     * Вызывает только private методы
     */
    public void invokePrivateMethods(Object target) {
        invokeAnnotatedMethodsByModifier(target, Modifier.PRIVATE);
    }

    /**
     * Вызывает только protected методы
     */
    public void invokeProtectedMethods(Object target) {
        invokeAnnotatedMethodsByModifier(target, Modifier.PROTECTED);
    }

    /**
     * Основной метод фильтрации по модификатору
     */
    private void invokeAnnotatedMethodsByModifier(Object target, int targetModifier) {
        if (target == null) {
            System.out.println("Ошибка: целевой объект не может быть null");
            return;
        }

        Class<?> clazz = target.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            InvokeMultiple annotation = method.getAnnotation(InvokeMultiple.class);

            if (annotation != null) {
                int modifiers = method.getModifiers();

                if (targetModifier == 0 ||
                        (targetModifier == Modifier.PUBLIC && Modifier.isPublic(modifiers)) ||
                        (targetModifier == Modifier.PRIVATE && Modifier.isPrivate(modifiers)) ||
                        (targetModifier == Modifier.PROTECTED && Modifier.isProtected(modifiers))) {

                    processAnnotatedMethod(target, method, annotation);
                }
            }
        }
    }

    /**
     * Обрабатывает отдельный аннотированный метод
     */
    private void processAnnotatedMethod(Object target, Method method, InvokeMultiple annotation) {
        System.out.println("Обработка метода: " + method.getName());

        boolean accessibilityChanged = false;

        try {
            if (!method.isAccessible()) {
                method.setAccessible(true);
                accessibilityChanged = true;
            }

            Object[] parameters = prepareMethodParameters(method);
            int invocationCount = annotation.times();

            for (int i = 0; i < invocationCount; i++) {
                invokeMethod(target, method, parameters);
            }

        } catch (Exception e) {
            System.err.println("Ошибка вызова метода " + method.getName() + ": " + e.getMessage());
        } finally {
            if (accessibilityChanged) {
                method.setAccessible(false);
            }
        }
    }

    /**
     * Подготавливает параметры для вызова метода
     */
    private Object[] prepareMethodParameters(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length == 0) {
            return null;
        }

        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = DEFAULT_VALUES.get(parameterTypes[i]);
            if (parameters[i] == null) {
                throw new UnsupportedOperationException(
                        "Неподдерживаемый тип параметра: " + parameterTypes[i].getName()
                );
            }
        }

        return parameters;
    }

    /**
     * Вызывает метод с подготовленными параметрами
     */
    private void invokeMethod(Object target, Method method, Object[] parameters) {
        try {
            method.invoke(target, parameters);
            System.out.print("Вызван метод: " + method.getName());
            if (parameters != null) {
                System.out.print(" с параметрами: ");
                for (Object param : parameters) {
                    System.out.print(param + " ");
                }
            }
            System.out.println();
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Ошибка вызова метода " + method.getName() + ": " + e.getCause().getMessage());
        }
    }
}