package files.ui.avaj.aircraft;

import files.ui.avaj.coordinates.Coordinates;

public class JetPlane extends Aircraft {
    
    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        
        switch (weather) {
            case "SUN":
                System.out.println("JetPlane#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                updateCoordinates(0, 10, 2);
                break;
            case "RAIN":
                System.out.println("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                updateCoordinates(0, 5, 0);
                break;
            case "FOG":
                System.out.println("JetPlane#" + name + "(" + id + "): Can't see anything in this fog.");
                updateCoordinates(0, 1, 0);
                break;
            case "SNOW":
                System.out.println("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
                updateCoordinates(0, 0, -7);
                break;
        }
    }
}
