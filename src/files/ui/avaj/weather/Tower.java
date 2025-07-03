package files.ui.avaj.weather;

import files.ui.avaj.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    protected List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }   
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}