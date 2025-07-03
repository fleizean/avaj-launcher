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
	rm -rf $(OUTPUT_DIR)/*.class
	rm -f sources.txt
	rm -f simulation.txt

# Help
help:
	@echo "Available targets:"
	@echo "  compile  - Compile all Java files to $(OUTPUT_DIR) directory"
	@echo "  run      - Compile and run with example scenario"
	@echo "  clean    - Remove all compiled files"
	@echo "  help     - Show this help message"

.PHONY: all compile run clean help