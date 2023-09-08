package workers;

public class Freelancer extends BaseEmloyee {
    private double workHourRate;

    public Freelancer(String name, String surname, String birthday, double workHourRate) {
        super(name, surname, birthday);
        this.workHourRate = workHourRate;
    }

    @Override
    public double salaryCalc() {
        return 20.8 * 8 * workHourRate;
    }

    @Override
    public String toString() {
        return String.format("%d. ФЛ %s %s, полных лет:%d, з/п:%.2f", id, name, surname, getAge(), salaryCalc());
    }
}
