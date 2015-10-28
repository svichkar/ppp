package org.LoggingLab;

import java.io.IOException;

public class FileNotFoundExp extends IOException {

    private String message = "";

    FileNotFoundExp(String message) {
	this.message = message;
    }

    public void printErrorMesssage() {
	System.out.println(message);
    }

}
