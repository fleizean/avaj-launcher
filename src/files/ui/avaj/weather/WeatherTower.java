package files.ui.avaj.weather;

import files.ui.avaj.aircraft.Aircraft;
import files.ui.avaj.aircraft.Flyable;
import files.ui.avaj.coordinates.Coordinates;

public class WeatherTower extends Tower {

    @Override
    public void register(Flyable flyable) {
        super.register(flyable);
        Aircraft aircraft = (Aircraft) flyable;
        String aircraftType = aircraft.getClass().getSimpleName();
        System.out.println("Tower says: " + aircraftType + "#" + aircraft.getName() + 
                          "(" + aircraft.getId() + ") registered to weather tower.");
    }

    @Override
    public void unregister(Flyable flyable) {
        super.unregister(flyable);
        Aircraft aircraft = (Aircraft) flyable;
        String aircraftType = aircraft.getClass().getSimpleName();
        System.out.println("Tower says: " + aircraftType + "#" + aircraft.getName() + 
                          "(" + aircraft.getId() + ") unregistered from weather tower.");
    }

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
    
    // Backward compatibility
    public void triggerWeatherChange() {
        changeWeather();
    }
}