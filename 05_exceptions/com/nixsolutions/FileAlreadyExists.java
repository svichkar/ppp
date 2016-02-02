package com.nixsolutions;

/**
 * Class FileAlreadyExists extends {@link Exception} class reports about
 * readiness file to work
 *
 * @author Dmitry Zinovyi
 */
public class FileAlreadyExists extends Exception {

    public FileAlreadyExists() {
        super("File ready for work");
    }

    public FileAlreadyExists(String message) {
        super(message);
    }

}
