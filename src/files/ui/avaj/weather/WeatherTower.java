package files.ui.avaj.weather;

import files.ui.avaj.aircraft.Aircraft;
import files.ui.avaj.aircraft.Flyable;
import files.ui.avaj.coordinates.Coordinates;
import java.util.ArrayList;
import java.util.List;

public class WeatherTower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        System.out.println("Tower says: " + ((Aircraft)flyable).getName() + 
                          "#" + ((Aircraft)flyable).getId() + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        System.out.println("Tower says: " + ((Aircraft)flyable).getName() + 
                          "#" + ((Aircraft)flyable).getId() + " unregistered from weather tower.");
    }

    protected void conditionChanged() {
        List<Flyable> currentObservers = new ArrayList<>(observers);
        for (Flyable flyable : currentObservers) {
            flyable.updateConditions();
        }
    }

    public void triggerWeatherChange() {
        conditionChanged();
    }    

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(coordinates);
    }
}
