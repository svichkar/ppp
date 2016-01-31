package com.nixsolutions.studentgrade.exception;

/**
 * Created by konstantin on 1/31/2016.
 */
public class CustomDaoException extends RuntimeException {

    public CustomDaoException(Exception e) {
        super(e);
    }
}
