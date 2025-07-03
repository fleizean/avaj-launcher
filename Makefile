# Compiler
JAVAC = javac
JAVA = java

# Directories
SRC_DIR = src
PACKAGE_DIR = ro/academyplus/avaj

# Main class
MAIN_CLASS = ro.academyplus.avaj.simulator.Simulator

# Default target
all: compile

# Compile
compile:
	find $(SRC_DIR) -name "*.java" > sources.txt
	$(JAVAC) @sources.txt

# Run with example scenario
run: compile
	$(JAVA) $(MAIN_CLASS) scenarios/scenario.txt

# Clean compiled files
clean:
	find $(SRC_DIR) -name "*.class" -delete
	rm -f sources.txt
	rm -f output/simulation.txt

# Help
help:
	@echo "Available targets:"
	@echo "  compile  - Compile all Java files"
	@echo "  run      - Compile and run with example scenario"
	@echo "  clean    - Remove all compiled files"
	@echo "  help     - Show this help message"

.PHONY: all compile run clean help