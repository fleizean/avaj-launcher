package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;

public class Helicopter extends Aircraft {
    // subject.pdf
    // • When a weather change occurs, each aircraft type needs to log a message, as seen in the example. 
    // The message format is: TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE. A funny message will be appreciated during the correction.
    
    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        
        switch (weather) {
            case "SUN" -> {
                System.out.println("Helicopter#" + name + "(" + id + "): This is hot.");
                updateCoordinates(10, 0, 2);
            }
            case "RAIN" -> {
                System.out.println("Helicopter#" + name + "(" + id + "): Flying in the rain is dangerous.");
                updateCoordinates(5, 0, 0);
            }
            case "FOG" -> {
                System.out.println("Helicopter#" + name + "(" + id + "): Navigation is difficult in fog.");
                updateCoordinates(1, 0, 0);
            }
            case "SNOW" -> {
                System.out.println("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
                updateCoordinates(0, 0, -12);
            }
        }
    }
}
