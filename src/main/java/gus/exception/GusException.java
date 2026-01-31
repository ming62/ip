package gus.exception;

/**
 * Represents exceptions specific to the Gus application.
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
