package workers;

public class Worker extends BaseEmloyee {
    public Worker(String name, String surname, String birthday, double salary) {
        super(name, surname, birthday);
        this.salary = salary;
    }

    @Override
    public double salaryCalc() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%d. НР %s %s, полных лет:%d, з/п:%.2f", id, name, surname, getAge(), salaryCalc());
    }
}
