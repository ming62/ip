package gus.exception;

/**
 * Represents an exception for the Gus application.
 */
public class GusException extends Exception {
    
    /**
     * Creates a GusException with an error message.
     * 
     * @param m The error message.
     */
    public GusException(String m) {
        super(m);
    }
    
}
