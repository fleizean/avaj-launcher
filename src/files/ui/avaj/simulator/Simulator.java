package files.ui.avaj.simulator;

import files.ui.avaj.aircraft.*;
import files.ui.avaj.coordinates.Coordinates;
import files.ui.avaj.exceptions.*;
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
            runSimulation(args[0]);
        } catch (InvalidScenarioException e) {
            System.err.println("Scenario Error: " + e.getMessage());
            System.exit(1);
        } catch (InvalidAircraftException e) {
            System.err.println("Aircraft Error: " + e.getMessage());
            System.exit(1);
        } catch (InvalidCoordinatesException e) {
            System.err.println("Coordinates Error: " + e.getMessage());
            System.exit(1);
        } catch (SimulationException e) {
            System.err.println("Simulation Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void runSimulation(String scenarioFile) 
            throws InvalidScenarioException, InvalidAircraftException, 
                   InvalidCoordinatesException, SimulationException, WeatherException {
        
        List<String> lines;
        try {
            lines = readFile(scenarioFile);
        } catch (IOException e) {
            throw new InvalidScenarioException("Could not read scenario file: " + scenarioFile, e);
        }
        
        if (lines.isEmpty()) {
            throw new InvalidScenarioException("Scenario file is empty: " + scenarioFile);
        }
        
        // Parse simulation count
        int simulationCount;
        try {
            simulationCount = Integer.parseInt(lines.get(0));
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException("Invalid simulation count: " + lines.get(0), e);
        }
        
        if (simulationCount <= 0) {
            throw new InvalidScenarioException("Simulation count must be positive: " + simulationCount);
        }
        
        if (simulationCount > 10000) {
            throw new InvalidScenarioException("Simulation count too large (max 10000): " + simulationCount);
        }
        
        WeatherTower tower = new WeatherTower();
        List<Flyable> aircrafts = new ArrayList<>();
        
        // Parse and create aircrafts
        for (int i = 1; i < lines.size(); i++) {
            try {
                Flyable aircraft = parseAircraftLine(lines.get(i), i + 1);
                aircrafts.add(aircraft);
            } catch (InvalidAircraftException | InvalidCoordinatesException e) {
                System.err.println("Warning: Skipping invalid aircraft on line " + (i + 1) + ": " + e.getMessage());
                // Continue with other aircrafts instead of failing completely
            }
        }
        
        if (aircrafts.isEmpty()) {
            throw new SimulationException("No valid aircrafts to simulate");
        }
        
        // Register aircrafts
        for (Flyable aircraft : aircrafts) {
            aircraft.registerTower(tower);
            tower.register(aircraft);
        }
        
        // Run simulation
        try {
            for (int i = 0; i < simulationCount; i++) {
                tower.triggerWeatherChange();
            }
        } catch (Exception e) {
            throw new SimulationException("Error during simulation execution", e);
        }
    }
    
    private static Flyable parseAircraftLine(String line, int lineNumber) 
            throws InvalidAircraftException, InvalidCoordinatesException {
        
        if (line == null || line.trim().isEmpty()) {
            throw new InvalidAircraftException("Line " + lineNumber + " is empty");
        }
        
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 5) {
            throw new InvalidAircraftException(
                "Line " + lineNumber + " has invalid format. Expected: TYPE NAME LONGITUDE LATITUDE HEIGHT. Got: " + line);
        }
        
        String type = parts[0];
        String name = parts[1];
        
        // Parse coordinates
        int longitude, latitude, height;
        try {
            longitude = Integer.parseInt(parts[2]);
            latitude = Integer.parseInt(parts[3]);
            height = Integer.parseInt(parts[4]);
        } catch (NumberFormatException e) {
            throw new InvalidCoordinatesException(
                "Line " + lineNumber + " has invalid coordinate values: " + line, e);
        }
        
        Coordinates coords = new Coordinates(longitude, latitude, height);
        return AircraftFactory.getInstance().newAircraft(type, name, coords);
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