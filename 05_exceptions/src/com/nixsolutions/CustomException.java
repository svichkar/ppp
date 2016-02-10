package com.nixsolutions;

/**
 * Created by pantiukhin on 2/10/2016.
 */
public class CustomException extends Exception {
    private String errorCause = "unknownException";

    public CustomException(String message) {
        super(message);
    }

    public String getErrorCause() {
        return this.errorCause;
    }

}
