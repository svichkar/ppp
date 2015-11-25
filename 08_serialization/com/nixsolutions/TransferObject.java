package com.nixsolutions;

import java.io.Serializable;

public class TransferObject implements Serializable {

	private static final long serialVersionUID = -383372516159043551L;
	private Long id;
	private String message;

	public TransferObject(Long id, String message) {
		this.id = id;
		this.message = message;
	}
	
	public void print() {
        System.out.println("Id: " + id);
        System.out.println("Message: " + message);
    }

}
