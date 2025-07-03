package files.ui.avaj.weather;

import files.ui.avaj.coordinates.Coordinates;

public class WeatherProvider {
    private static WeatherProvider instance; // Singleton için
    private String[] conditions = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int seed = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight()) % 4;
        return conditions[seed];
    }
}
