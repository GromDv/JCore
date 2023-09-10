import classes.*;
import exceptions.*;

public class ExceptionsApp {
    public static void main(String[] args) {

        String[][] array = UsefullArrays.getArray (4, 4);
        System.out.println(UsefullArrays.print(array));
        System.out.println();

        String tmp = "";
        try {
            tmp = UsefullArrays.getSum(array);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(tmp);
        System.out.println();

        String[][] array2 = UsefullArrays.getArray(4, 6);
        System.out.println(UsefullArrays.print(array2));
        System.out.println();

        tmp = "";
        try {
            tmp = UsefullArrays.getSum(array2);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(tmp);

    }
}
