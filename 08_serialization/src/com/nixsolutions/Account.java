package com.nixsolutions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    public Account(Long id, String name, String role) {
	this.id = id;
	this.name = name;
	this.role = role;

    }

    private Long id;
    private String name;
    private String role;

    @Override
    public String toString() {
	return id + "\t" + name + "\t" + role;
    }
    
    public  byte[] serialize( Serializable o ) throws IOException {
	
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject(o );
        oos.close();
        return baos.toByteArray();
       
    }
    
    

}
