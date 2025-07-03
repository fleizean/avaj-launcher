# Compiler
JAVAC = javac
JAVA = java

# Directories
SRC_DIR = src
OUTPUT_DIR = output
PACKAGE_DIR = files/ui/avaj

# Main class
MAIN_CLASS = files.ui.avaj.simulator.Simulator

# Default target
all: compile

# Create output directory if it doesn't exist
$(OUTPUT_DIR):
	mkdir -p $(OUTPUT_DIR)

# Compile
compile: $(OUTPUT_DIR)
	find $(SRC_DIR) -name "*.java" > sources.txt
	$(JAVAC) -d $(OUTPUT_DIR) @sources.txt

# Run with example scenario
run: compile
	$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/scenario.txt

# Clean compiled files
clean:
	rm -rf $(OUTPUT_DIR)
	rm -f sources.txt
	rm -f simulation.txt
	rm -rf scenarios

# Help
help:
	@echo "Available targets:"
	@echo "  compile  - Compile all Java files to $(OUTPUT_DIR) directory"
	@echo "  run      - Compile and run with example scenario"
	@echo "  clean    - Remove all compiled files"
	@echo "  help     - Show this help message"
	@echo "  create-bonus-scenarios - Create bonus test scenario files in scenarios directory"

create-bonus-scenarios: $(SCENARIO_DIR)
	@echo "Creating bonus test scenario files..."
	@mkdir -p scenarios
	@echo "25" > scenarios/scenario.txt
	@echo "Baloon B1 2 3 20" >> scenarios/scenario.txt
	@echo "Baloon B2 1 8 66" >> scenarios/scenario.txt
	@echo "JetPlane J1 23 44 32" >> scenarios/scenario.txt
	@echo "Helicopter H1 654 33 20" >> scenarios/scenario.txt
	@echo "Helicopter H2 22 33 44" >> scenarios/scenario.txt
	@echo "Helicopter H3 98 68 99" >> scenarios/scenario.txt
	@echo "Baloon B3 102 22 34" >> scenarios/scenario.txt
	@echo "JetPlane J2 11 99 768" >> scenarios/scenario.txt
	@echo "Helicopter H4 223 23 54" >> scenarios/scenario.txt

	@echo "invalid_number" > scenarios/test_invalid_simulation_count.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_invalid_simulation_count.txt
	
	@echo "-5" > scenarios/test_negative_simulation_count.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_negative_simulation_count.txt
	
	@echo "0" > scenarios/test_zero_simulation_count.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_zero_simulation_count.txt
	
	@echo "50000" > scenarios/test_large_simulation_count.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_large_simulation_count.txt
	
	@echo "5" > scenarios/test_invalid_aircraft_type.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_invalid_aircraft_type.txt
	@echo "InvalidType X1 20 20 30" >> scenarios/test_invalid_aircraft_type.txt
	@echo "JetPlane J1 30 30 40" >> scenarios/test_invalid_aircraft_type.txt
	
	@echo "3" > scenarios/test_invalid_aircraft_format.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_invalid_aircraft_format.txt
	@echo "JetPlane J1 20 20" >> scenarios/test_invalid_aircraft_format.txt
	@echo "Helicopter H1 30 30 40 50" >> scenarios/test_invalid_aircraft_format.txt
	
	@echo "3" > scenarios/test_empty_aircraft_name.txt
	@echo 'Baloon "" 10 10 50' >> scenarios/test_empty_aircraft_name.txt
	@echo "JetPlane J1 20 20 30" >> scenarios/test_empty_aircraft_name.txt
	@echo "Helicopter H1 30 30 40" >> scenarios/test_empty_aircraft_name.txt
	
	@echo "3" > scenarios/test_null_aircraft_type.txt
	@echo '"" B1 10 10 50' >> scenarios/test_null_aircraft_type.txt
	@echo "JetPlane J1 20 20 30" >> scenarios/test_null_aircraft_type.txt
	@echo "Helicopter H1 30 30 40" >> scenarios/test_null_aircraft_type.txt
	
	@echo "5" > scenarios/test_invalid_coordinates.txt
	@echo "Baloon B1 abc 10 50" >> scenarios/test_invalid_coordinates.txt
	@echo "JetPlane J1 20 def 30" >> scenarios/test_invalid_coordinates.txt
	@echo "Helicopter H1 30 30 xyz" >> scenarios/test_invalid_coordinates.txt
	
	@echo "3" > scenarios/test_negative_coordinates.txt
	@echo "Baloon B1 -10 10 50" >> scenarios/test_negative_coordinates.txt
	@echo "JetPlane J1 20 -20 30" >> scenarios/test_negative_coordinates.txt
	@echo "Helicopter H1 30 30 40" >> scenarios/test_negative_coordinates.txt
	
	@echo "3" > scenarios/test_negative_height.txt
	@echo "Baloon B1 10 10 -50" >> scenarios/test_negative_height.txt
	@echo "JetPlane J1 20 20 30" >> scenarios/test_negative_height.txt
	@echo "Helicopter H1 30 30 40" >> scenarios/test_negative_height.txt
	
	@touch scenarios/test_empty_scenario.txt
	
	@echo "5" > scenarios/test_no_aircrafts.txt
	
	@echo "invalid_count" > scenarios/test_mixed_errors.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_mixed_errors.txt
	@echo "InvalidType X1 abc def 30" >> scenarios/test_mixed_errors.txt
	@echo 'JetPlane "" 20 20 30' >> scenarios/test_mixed_errors.txt
	@echo "Helicopter H1 -10 -20 -30" >> scenarios/test_mixed_errors.txt
	
	@echo "3" > scenarios/test_malformed_lines.txt
	@echo "Baloon B1 10 10 50" >> scenarios/test_malformed_lines.txt
	@echo "    " >> scenarios/test_malformed_lines.txt
	@echo "JetPlane J1 20 20 30" >> scenarios/test_malformed_lines.txt
	@echo "Helicopter H1 30 30 40    extra_data" >> scenarios/test_malformed_lines.txt
	
	@echo "3" > scenarios/test_height_capping.txt
	@echo "Baloon B1 10 10 150" >> scenarios/test_height_capping.txt
	@echo "JetPlane J1 20 20 200" >> scenarios/test_height_capping.txt
	@echo "Helicopter H1 30 30 1000" >> scenarios/test_height_capping.txt
	
	@echo "1" > scenarios/test_edge_values.txt
	@echo "Baloon B1 0 0 0" >> scenarios/test_edge_values.txt
	@echo "JetPlane J1 2147483647 2147483647 100" >> scenarios/test_edge_values.txt
	@echo "Helicopter H1 1 1 1" >> scenarios/test_edge_values.txt
	
	@echo "   5   " > scenarios/test_whitespace_handling.txt
	@echo "  Baloon   B1   10   10   50  " >> scenarios/test_whitespace_handling.txt
	@echo "JetPlane	J1	20	20	30" >> scenarios/test_whitespace_handling.txt
	@echo "   Helicopter   H1   30   30   40   " >> scenarios/test_whitespace_handling.txt
	
	@echo "3" > scenarios/test_case_sensitivity.txt
	@echo "baloon b1 10 10 50" >> scenarios/test_case_sensitivity.txt
	@echo "JETPLANE j1 20 20 30" >> scenarios/test_case_sensitivity.txt
	@echo "hElIcOpTeR h1 30 30 40" >> scenarios/test_case_sensitivity.txt
	
	@echo "Bonus test scenario files created!"

test-scenario: compile
	@echo "=== Running Example Scenario ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/scenario.txt 2>&1

test-invalid-simulation-count: compile
	@echo "=== Testing Invalid Simulation Count ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_invalid_simulation_count.txt 2>&1

test-negative-simulation-count: compile
	@echo "=== Testing Negative Simulation Count ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_negative_simulation_count.txt 2>&1

test-zero-simulation-count: compile
	@echo "=== Testing Zero Simulation Count ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_zero_simulation_count.txt 2>&1

test-large-simulation-count: compile
	@echo "=== Testing Large Simulation Count ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_large_simulation_count.txt 2>&1

test-invalid-aircraft-type: compile
	@echo "=== Testing Invalid Aircraft Type ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_invalid_aircraft_type.txt 2>&1

test-invalid-aircraft-format: compile
	@echo "=== Testing Invalid Aircraft Format ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_invalid_aircraft_format.txt 2>&1

test-empty-aircraft-name: compile
	@echo "=== Testing Empty Aircraft Name ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_empty_aircraft_name.txt 2>&1

test-invalid-coordinates: compile
	@echo "=== Testing Invalid Coordinates ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_invalid_coordinates.txt 2>&1

test-negative-coordinates: compile
	@echo "=== Testing Negative Coordinates ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_negative_coordinates.txt 2>&1

test-negative-height: compile
	@echo "=== Testing Negative Height ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_negative_height.txt 2>&1

test-empty-scenario: compile
	@echo "=== Testing Empty Scenario ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_empty_scenario.txt 2>&1

test-no-aircrafts: compile
	@echo "=== Testing No Aircrafts ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_no_aircrafts.txt 2>&1

test-mixed-errors: compile
	@echo "=== Testing Mixed Errors ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_mixed_errors.txt 2>&1

test-height-capping: compile
	@echo "=== Testing Height Capping ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_height_capping.txt 2>&1

test-nonexistent-file: compile
	@echo "=== Testing Nonexistent File ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/nonexistent_file.txt 2>&1

test-case-sensitivity: compile
	@echo "=== Testing Case Sensitivity ==="
	-$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS) scenarios/test_case_sensitivity.txt 2>&1

test-all-bonus: create-bonus-scenarios compile
	@echo "==============================================="
	@echo "           RUNNING ALL BONUS TESTS           "
	@echo "==============================================="
	$(MAKE) test-scenario
	@echo "-----------------------------------------------"
	$(MAKE) test-invalid-simulation-count
	@echo "-----------------------------------------------"
	$(MAKE) test-negative-simulation-count
	@echo "-----------------------------------------------"
	$(MAKE) test-zero-simulation-count
	@echo "-----------------------------------------------"
	$(MAKE) test-large-simulation-count
	@echo "-----------------------------------------------"
	$(MAKE) test-invalid-aircraft-type
	@echo "-----------------------------------------------"
	$(MAKE) test-invalid-aircraft-format
	@echo "-----------------------------------------------"
	$(MAKE) test-empty-aircraft-name
	@echo "-----------------------------------------------"
	$(MAKE) test-invalid-coordinates
	@echo "-----------------------------------------------"
	$(MAKE) test-negative-coordinates
	@echo "-----------------------------------------------"
	$(MAKE) test-negative-height
	@echo "-----------------------------------------------"
	$(MAKE) test-empty-scenario
	@echo "-----------------------------------------------"
	$(MAKE) test-no-aircrafts
	@echo "-----------------------------------------------"
	$(MAKE) test-mixed-errors
	@echo "-----------------------------------------------"
	$(MAKE) test-height-capping
	@echo "-----------------------------------------------"
	$(MAKE) test-nonexistent-file
	@echo "-----------------------------------------------"
	$(MAKE) test-case-sensitivity
	@echo "==============================================="
	@echo "           BONUS TESTS COMPLETED             "
	@echo "==============================================="



.PHONY: all compile run clean help create-bonus-scenarios test-all-bonus test-invalid-simulation-count test-negative-simulation-count test-zero-simulation-count test-large-simulation-count test-invalid-aircraft-type test-invalid-aircraft-format test-empty-aircraft-name test-invalid-coordinates test-negative-coordinates test-negative-height test-empty-scenario test-no-aircrafts test-mixed-errors test-height-capping test-nonexistent-file test-case-sensitivity