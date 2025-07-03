package files.ui.avaj.aircraft;

import files.ui.avaj.weather.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
