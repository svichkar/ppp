package com.nixsolutions;

public class FileExistsException extends RuntimeException {

    private static final long serialVersionUID = -8529271856935699573L;

    /**
     * Constructs an instance of this class.
     * 
     * @param message
     *            The message with details.
     */
    public FileExistsException(String message) {
        super(message);
    }
}
