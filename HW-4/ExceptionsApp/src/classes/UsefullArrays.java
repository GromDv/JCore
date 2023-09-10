package classes;

import exceptions.*;
import java.util.Random;

public class UsefullArrays {
    /**
     * Метод генерирует случайный двумерный массив заданной размерности
     * @param x
     * @param y
     * @return
     */
    public static String[][] getArray(int x, int y) {
        String[][] array = new String[x][y];
        Random rnd = new Random();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (rnd.nextInt(5) == 1)
                    // в 20% случаев генерируем ошибку - строковое выражение
                    array[i][j] = "--";
                else
                    // заполняем случайным числом от 10 до 99
                    array[i][j] = String.valueOf(rnd.nextInt(10, 100));
            }
        }
        return array;
    }

    /**
     * Метод выводит массив в строку.
     * @param arr
     * @return
     */
    public static String print(String[][] arr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result.append(arr[i][j]);
                result.append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Метод конвертирует все строковые элементы массива в целые и подсчитывает их сумму.
     * @param array - массив размером строго 4 х 4
     * @return
     * @throws MyArraySizeException - если массив не соответствует размеру
     */
    public static String getSum(String[][] array) throws MyArraySizeException {
        int sum = 0;

        if (array.length != 4 || array[0].length != 4)
            throw new MyArraySizeException("Размер массива не соответствует заданному!");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int tmp = 0;

                try {
                    if (checkElementForIntPossibility(array[i][j]))
                        tmp = Integer.parseInt(array[i][j]);
                    else
                        throw new MyArrayDataException(i, j);
                    sum += tmp;
                } catch (MyArrayDataException e) {
                    System.out.println(e.myMessage());
                }
            }
        }

        return "Сумма элементов = " + sum;
    }

    /**
     * Метод проверяет возможность конвертации в int.
     * @param e
     * @return
     */
    private static boolean checkElementForIntPossibility(String e) {
        for (char c : e.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
