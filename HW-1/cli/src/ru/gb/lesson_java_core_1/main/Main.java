package ru.gb.lesson_java_core_1.main;

import ru.gb.lesson_java_core_1.addon.Decorator;
import ru.gb.lesson_java_core_1.addon.Operation;

/**
 * Основной класс приложения
 */
public class Main {
    /**
     * Точка входа в программу
     * @param args - стандартные аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println(Decorator.decorate(Operation.add(3, 5)));
    }
}