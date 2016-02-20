package com.manetskiy;

public class FileExsistsException extends RuntimeException {
    public FileExsistsException() {
    }

    public FileExsistsException(String msg) {
        super(msg);
    }
}
