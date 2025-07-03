package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;
import files.ui.avaj.weather.WeatherTower;

public abstract class Aircraft implements Flyable {
    // subject.pdf
    // • Each time an aircraft is created, it receives a unique ID. There can’t be 2 aircrafts with the same ID.
    // • If an aircraft reaches height 0 or needs to go below it, the aircraft lands, unregisters from the weather tower and logs a message.

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;
    private static long nextId = 1; // static variable to keep track of the next ID

    protected Aircraft(String name, Coordinates coordinates) {
        // Here we assign a unique ID to each aircraft instance
        this.id = nextId += 1; // assign a unique ID
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
        
        // Here if the height is 0, we log the landing message and unregister from the weather tower
        if (newHeight == 0) {
            System.out.println(name + "#" + id + " landing.");
            weatherTower.unregister(this);
        }
    }
}
