package workers;

import java.util.ArrayList;
import java.util.Iterator;

public class Corporation implements Iterable<BaseEmloyee> {
    private ArrayList<BaseEmloyee> register = new ArrayList<>();

    public void add(BaseEmloyee x) {
        register.add(x);
    }

    @Override
    public Iterator<BaseEmloyee> iterator() {
        return register.iterator();
    }
}
