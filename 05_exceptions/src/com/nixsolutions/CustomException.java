package com.nixsolutions;

/**
 * Created by sobolenko on 2/2/2016.
 */
public class CustomException extends RuntimeException {
    String message = "";

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
