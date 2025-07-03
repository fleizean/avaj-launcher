# Avaj Launcher

A Java-based aircraft simulation system implementing core Object-Oriented Design Patterns for weather-based flight simulation.

## Overview

Avaj Launcher is a minimal aircraft simulation program that demonstrates the implementation of essential design patterns in Java. The project simulates aircraft behavior in response to weather changes, showcasing Observer, Singleton, and Factory patterns in a practical aviation context.

## Purpose

This project serves as an introduction to:
- **UML Class Diagram** interpretation and implementation
- **Design Patterns** (Observer, Singleton, Factory)
- **Java OOP** principles in practice
- Clean code architecture and maintainable design

## Architecture

### Design Patterns Implemented

#### 1. Observer Pattern
- **WeatherTower** acts as the Subject
- **Aircraft** instances are Observers
- Weather changes automatically notify all registered aircraft

#### 2. Singleton Pattern
- **WeatherProvider** ensures single instance across the system
- **AircraftFactory** provides centralized aircraft creation

#### 3. Factory Pattern
- **AircraftFactory** abstracts aircraft creation logic
- Supports runtime determination of aircraft types

### Class Structure

```
src/files/ui/avaj/
├── aircraft/
│   ├── Aircraft.java          # Abstract base class
│   ├── AircraftFactory.java   # Factory pattern implementation
│   ├── Baloon.java           # Balloon aircraft implementation
│   ├── Flyable.java          # Observer interface
│   ├── Helicopter.java       # Helicopter aircraft implementation
│   └── JetPlane.java         # Jet plane aircraft implementation
├── coordinates/
│   └── Coordinates.java      # 3D position system
├── exceptions/
│   ├── InvalidAircraftException.java
│   ├── InvalidCoordinatesException.java
│   ├── InvalidScenarioException.java
│   ├── SimulationException.java
│   └── WeatherException.java
├── simulator/
│   └── Simulator.java        # Main simulation controller
└── weather/
    ├── Tower.java            # Abstract observer pattern base
    ├── WeatherProvider.java  # Singleton weather generator
    └── WeatherTower.java     # Concrete observer implementation
```

## Features

### Weather System
- **4 Weather Types**: SUN, RAIN, FOG, SNOW
- **3D Coordinate-based**: Each position has unique weather
- **Dynamic Generation**: Weather changes affect aircraft behavior

### Aircraft Types
Each aircraft responds differently to weather conditions:

**JetPlane**
- SUN: Latitude +10, Height +2
- RAIN: Latitude +5
- FOG: Latitude +1
- SNOW: Height -7

**Helicopter**
- SUN: Longitude +10, Height +2
- RAIN: Longitude +5
- FOG: Longitude +1
- SNOW: Height -12

**Balloon**
- SUN: Longitude +2, Height +4
- RAIN: Height -5
- FOG: Height -3
- SNOW: Height -15

### Simulation Rules
- **Coordinate Constraints**: All coordinates must be positive
- **Height Limits**: 0-100 range (capped at boundaries)
- **Unique IDs**: Each aircraft receives a unique identifier
- **Landing Logic**: Aircraft at height 0 land and unregister
- **Registration Logging**: All tower interactions are logged

## Installation & Usage

### Prerequisites
- Java 8 or higher
- `javac` and `java` commands available in PATH

### Compilation
```bash
# Using provided Makefile
make compile

# Or manually
find src -name "*.java" > sources.txt
javac -d output @sources.txt
```

### Execution
```bash
# Using Makefile
make run

# Or manually
java -cp output files.ui.avaj.simulator.Simulator scenarios/scenario.txt
```

### Scenario File Format
```
25                          # Number of simulation cycles
Baloon B1 2 3 20           # TYPE NAME LONGITUDE LATITUDE HEIGHT
JetPlane J1 23 44 32
Helicopter H1 654 33 20
```

## Testing

The project includes comprehensive test scenarios:

```bash
# Run all tests
make test-all-bonus

# Specific test categories
make test-invalid-simulation-count
make test-negative-coordinates
make test-height-capping
```

### Test Coverage
- Invalid simulation counts
- Malformed aircraft data
- Boundary conditions
- Edge cases and error handling

## Output

The simulation generates detailed logs including:
- Aircraft registration/unregistration
- Weather-based behavior messages
- Landing notifications
- Error handling for invalid inputs

### Example Output
```
Tower says: Balloon#B1(1) registered to weather tower.
Tower says: JetPlane#J1(2) registered to weather tower.
Balloon#B1(1): Let's enjoy the good weather and take some pics.
JetPlane#J1(2): It's raining. Better watch out for lightings.
Balloon#B1(1): Damn you rain! You messed up my balloon.
Balloon#B1(1) landing.
Tower says: Balloon#B1(1) unregistered from weather tower.
```

## Error Handling

Robust exception handling includes:
- **InvalidScenarioException**: Malformed scenario files
- **InvalidAircraftException**: Unknown aircraft types
- **InvalidCoordinatesException**: Invalid coordinate values
- **SimulationException**: Runtime simulation errors

## Technical Specifications

- **Language**: Java 8+ (LTS compatible)
- **Build System**: Makefile with comprehensive targets
- **Package Structure**: Proper Java package conventions
- **Design Patterns**: Observer, Singleton, Factory
- **Testing**: Extensive edge case coverage
