package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;
import files.ui.avaj.exceptions.InvalidAircraftException;
import files.ui.avaj.exceptions.InvalidCoordinatesException;

public class AircraftFactory {
    private static AircraftFactory instance;
    
    private AircraftFactory() {}
    
    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }
    
    public Flyable newAircraft(String type, String name, Coordinates coordinates) 
            throws InvalidAircraftException, InvalidCoordinatesException {
        
        // Validate coordinates
        if (coordinates == null) {
            throw new InvalidCoordinatesException("Coordinates cannot be null");
        }
        
        if (coordinates.getLongitude() < 0 || coordinates.getLatitude() < 0) {
            throw new InvalidCoordinatesException(
                "Longitude and latitude must be positive numbers. " +
                "Got: longitude=" + coordinates.getLongitude() + 
                ", latitude=" + coordinates.getLatitude());
        }
        
        if (coordinates.getHeight() < 0) {
            throw new InvalidCoordinatesException(
                "Height cannot be negative. Got: height=" + coordinates.getHeight());
        }
        
        // Validate aircraft type and name
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidAircraftException("Aircraft type cannot be null or empty");
        }
        
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidAircraftException("Aircraft name cannot be null or empty");
        }
        
        String normalizedType = type.toLowerCase();
        
        return switch (normalizedType) {
            case "baloon" -> new Baloon(name, coordinates);
            case "jetplane" -> new JetPlane(name, coordinates);
            case "helicopter" -> new Helicopter(name, coordinates);
            default -> throw new InvalidAircraftException(
                "Unknown aircraft type: '" + type + "'. " +
                "Supported types: Baloon, JetPlane, Helicopter");
        };
    }
}