# Define variables
PROJECT_NAME = java-chess
VERSION = 1.0-SNAPSHOT
JAR_FILE = target/$(PROJECT_NAME)-$(VERSION).jar

# Default target (build and run)
all: build run

# Build the project using Maven
build:
	mvn clean package

# Run the JAR file
run: $(JAR_FILE)
	java -jar $(JAR_FILE)

# Clean the project (removes compiled files)
clean:
	mvn clean
	rm -rf target

# Install dependencies
install:
	mvn install

# Display help
help:
	@echo "Usage: make [target]"
	@echo "Available targets:"
	@echo "  build      - Compile and package the project"
	@echo "  run        - Run the JAR file"
	@echo "  clean      - Clean compiled files"
	@echo "  install    - Install dependencies"
	@echo "  help       - Show this help message"

.PHONY: all build run clean install help
