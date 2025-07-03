package files.ui.avaj.exceptions;

public class InvalidAircraftException extends Exception {
    public InvalidAircraftException(String message) {
        super(message);
    }
    
    public InvalidAircraftException(String message, Throwable cause) {
        super(message, cause);
    }
}
