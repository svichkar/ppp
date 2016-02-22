package com.nixsolutions;

/**
 * Class implements our custom exception that inherits RunTimeException class
 * @author Sirotkin Mikhail
 */
public class CustomRTException extends RuntimeException{
    /**
     * Constructor
     * @param message of exception
     */
    public CustomRTException(String message){
        super (message);
    }
}
