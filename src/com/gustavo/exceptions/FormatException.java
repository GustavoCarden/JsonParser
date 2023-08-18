package com.gustavo.exceptions;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class FormatException extends Exception {
    
    /**
     *
     * @param message
     */
    public FormatException(String message) {
        super(message);
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
