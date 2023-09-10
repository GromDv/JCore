package exceptions;

/**
 * Исключение при несоответствии размера массива заданному
 */
public class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}
