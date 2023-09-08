import workers.BaseEmloyee;
import workers.Corporation;
import workers.EmployeeSalaryComparator;

import java.util.ArrayList;
import java.util.Collections;

public class WorkersApp {
    public static void main(String[] args) {

        ArrayList<BaseEmloyee> spisok = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            spisok.add(BaseEmloyee.getInstance());
        }
        Collections.sort(spisok);
        for (int i = 0; i < 10; i++) {
            System.out.println(spisok.get(i).toString());
        }
        System.out.println();

        Collections.sort(spisok, new EmployeeSalaryComparator());
        for (int i = 0; i < 10; i++) {
            System.out.println(spisok.get(i).toString());
        }
        System.out.println();

        Corporation ics_corp = new Corporation();
        for (int i = 0; i < 10; i++) {
            ics_corp.add(BaseEmloyee.getInstance());
        }
        for (BaseEmloyee employee : ics_corp) {
            System.out.println(employee);
        }
    }
}
