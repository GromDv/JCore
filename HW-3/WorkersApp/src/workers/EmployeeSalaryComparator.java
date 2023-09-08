package workers;

import java.util.Comparator;

public class EmployeeSalaryComparator implements Comparator<BaseEmloyee> {
    @Override
    public int compare(BaseEmloyee o1, BaseEmloyee o2) {
        return Double.compare(o1.salaryCalc(), o2.salaryCalc());
    }
}
