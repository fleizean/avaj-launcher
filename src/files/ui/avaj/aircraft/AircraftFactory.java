package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;

public class AircraftFactory {
    
    public static Flyable newAircraft(String type, String name, Coordinates coordinates) {
        switch (type.toLowerCase()) {
            case "balloon":
                return new Balloon(name, coordinates);
            case "jetplane":
                return new JetPlane(name, coordinates);
            case "helicopter":
                return new Helicopter(name, coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + type);
        }
    }
}
