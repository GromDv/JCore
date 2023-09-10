package exceptions;

/**
 * Исключение при невозможности конвертации целое число
 */
public class MyArrayDataException extends Exception {
    private int i,j;

    public MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }
    public String myMessage() {
        return String.format("Пропущено нечисловое выражение в элементе [%d][%d]",i,j);
    }
}
