package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;
import files.ui.avaj.weather.WeatherTower;

public abstract class Aircraft implements Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;
    private static long nextId = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId++;
        this.name = name;
        this.coordinates = coordinates;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    protected void updateCoordinates(int deltaLongitude, int deltaLatitude, int deltaHeight) {
        int newLongitude = Math.max(0, coordinates.getLongitude() + deltaLongitude);
        int newLatitude = Math.max(0, coordinates.getLatitude() + deltaLatitude);
        int newHeight = Math.max(0, Math.min(100, coordinates.getHeight() + deltaHeight));
        
        this.coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
        
        if (newHeight == 0) {
            System.out.println(name + "#" + id + " landing.");
            weatherTower.unregister(this);
        }
    }
}
