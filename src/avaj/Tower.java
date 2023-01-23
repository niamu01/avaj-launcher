package avaj;

import java.util.ArrayList;
import java.util.List;

public class Tower {
//    private Flyable observers[];
    private static ArrayList<Flyable> observers = new ArrayList<Flyable>();

//    private ArrayList<Flyable> getObservers() {
//        return this.observers;
//    }
    public void register(Flyable flyable) {
        observers.add(flyable);
//        System.out.println(observers.size() + "size");
    };
    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    };
    protected void conditionsChanged() {
//        System.out.println(observers.size());
        for (int i = 0; i < observers.size(); i++) {
//            System.out.println(i);
            observers.get(i).updateConditions();
        }
    };
}
