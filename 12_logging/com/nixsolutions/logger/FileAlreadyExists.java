package com.nixsolutions.logger;

/**
 * Class FileAlreadyExists extends {@link Exception} class reports about
 * readiness file to work
 *
 * @author Dmitry Zinovyi
 */
public class FileAlreadyExists extends RuntimeException {

    public FileAlreadyExists() {
        super("File is already exist");
    }

    public FileAlreadyExists(String message) {
        super(message);
    }

}
