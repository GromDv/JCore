package workers;

import java.time.LocalDate;
import java.util.Random;

abstract public class BaseEmloyee implements CalcOfSalalry, Comparable {
    //region Fields
    private static int counter;
    protected int id;
    protected String name;
    protected String surname;

    public String getSurname() {
        return surname;
    }

    protected LocalDate birthday;
    protected double salary;
    protected static String[] names = new String[]{"Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман"};
    protected static String[] surNames = new String[]{"Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов"};
    protected static Random random = new Random();

    //endregion

    //region Methods
    public BaseEmloyee(String name, String surname, String birthday) {
        this.id = counter;
        counter++;
        this.name = name;
        this.surname = surname;
        this.birthday = LocalDate.parse(birthday);
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public static BaseEmloyee getInstance() {
        LocalDate bdate = LocalDate.of(random.nextInt(1975, 2005), random.nextInt(1, 12), random.nextInt(1, 28));
        if (random.nextInt(2) == 0) {
            return new Worker(
                    names[random.nextInt(surNames.length)],
                    surNames[random.nextInt(surNames.length)],
                    String.format("%s", bdate),
                    random.nextInt(30000, 250000));
        } else {
            return new Freelancer(
                    names[random.nextInt(surNames.length)],
                    surNames[random.nextInt(surNames.length)],
                    String.format("%s", bdate),
                    random.nextInt(200, 1000));
        }
    }

    @Override
    public double salaryCalc() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        BaseEmloyee o2 = (BaseEmloyee) o;
        return this.surname.compareTo(o2.surname);
    }
    //endregion
}
