package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;

public class JetPlane extends Aircraft {
    // // subject.pdf
    // • When a weather change occurs, each aircraft type needs to log a message, as seen in the example. 
    // The message format is: TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE. A funny message will be appreciated during the correction.
    
    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        
        switch (weather) {
            case "SUN" -> {
                System.out.println("JetPlane#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                updateCoordinates(0, 10, 2);
            }
            case "RAIN" -> {
                System.out.println("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                updateCoordinates(0, 5, 0);
            }
            case "FOG" -> {
                System.out.println("JetPlane#" + name + "(" + id + "): Can't see anything in this fog.");
                updateCoordinates(0, 1, 0);
            }
            case "SNOW" -> {
                System.out.println("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
                updateCoordinates(0, 0, -7);
            }
        }
    }
}
