package files.ui.avaj.simulator;

import files.ui.avaj.aircraft.*;
import files.ui.avaj.coordinates.Coordinates;
import files.ui.avaj.weather.WeatherTower;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java files.ui.avaj.simulator.Simulator <scenario_file>");
            System.exit(1);
        }
        
        try {
            List<String> lines = readFile(args[0]);
            int simulationCount = Integer.parseInt(lines.get(0));
            
            WeatherTower tower = new WeatherTower();
            List<Flyable> aircrafts = new ArrayList<>();
            
            // Create and register aircrafts
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(" ");
                if (parts.length != 5) continue;
                
                String type = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                
                if (height < 0 || height > 100) {
                    System.err.println("Invalid height: " + height);
                    continue;
                }
                
                Coordinates coords = new Coordinates(longitude, latitude, height);
                Flyable aircraft = AircraftFactory.getInstance().newAircraft(type, name, coords);
                aircrafts.add(aircraft);
                aircraft.registerTower(tower);
                tower.register(aircraft);
            }
            
            // Run simulation
            for (int i = 0; i < simulationCount; i++) {
                tower.triggerWeatherChange();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static List<String> readFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }
}
