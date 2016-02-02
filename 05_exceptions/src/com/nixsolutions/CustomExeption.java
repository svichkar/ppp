package com.nixsolutions;
/**
 * Created by sobolenko on 2/2/2016.
 */
public class CustomExeption extends RuntimeException {
    String message = "";

    public CustomExeption(Throwable cause) {
        super(cause);
    }

    public CustomExeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
