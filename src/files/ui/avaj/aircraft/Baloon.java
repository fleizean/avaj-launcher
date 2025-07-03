package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;

public class Baloon extends Aircraft {
    // subject.pdf
    // • When a weather change occurs, each aircraft type needs to log a message, as seen in the example. 
    // The message format is: TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE. A funny message will be appreciated during the correction.
    
    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        
        switch (weather) {
            case "SUN" -> {
                System.out.println("Baloon#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                updateCoordinates(2, 0, 4);
            }
            case "RAIN" -> {
                System.out.println("Baloon#" + name + "(" + id + "): Damn you rain! You messed up my balloon.");
                updateCoordinates(0, 0, -5);
            }
            case "FOG" -> {
                System.out.println("Baloon#" + name + "(" + id + "): Visibility is poor in this fog.");
                updateCoordinates(0, 0, -3);
            }
            case "SNOW" -> {
                System.out.println("Baloon#" + name + "(" + id + "): It's snowing. We're gonna crash.");
                updateCoordinates(0, 0, -15);
            }
        }
    }
}
