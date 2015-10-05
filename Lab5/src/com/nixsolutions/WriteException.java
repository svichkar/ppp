package com.nixsolutions;

import java.io.IOException;

public class WriteException extends IOException {
	private static final long serialVersionUID = 1L;

	public WriteException(String message) {
		System.out.println(message);
	}
}
