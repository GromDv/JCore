package ru.gb.lesson_java_core_1.addon;

/**
 * Пакет декораторов
 */
public class Decorator {
    /**
     * Функция декорирования числа для вывода в консоль
     * @param - входной параметр
     * @return
     */
    public static String decorate(int param) {
        return String.format("Here is result: %d.", param);
    }
}
