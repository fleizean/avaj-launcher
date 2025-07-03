package files.ui.avaj.exceptions;

public class InvalidCoordinatesException extends Exception {
    public InvalidCoordinatesException(String message) {
        super(message);
    }
    
    public InvalidCoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }
}
