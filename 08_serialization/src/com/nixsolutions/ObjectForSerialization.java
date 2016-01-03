package com.nixsolutions;

import java.io.Serializable;

/**
 * @author Sirotkin Mikhail
 * Class that represent object for serialization/deserialization
 */
public class ObjectForSerialization implements Serializable {
    private Long id;
    private String message;

    private static final String introdutionMessage = "Hello, World!";

    /**
     * Constructor
     * @param id
     * @param message
     */
    public ObjectForSerialization(Long id, String message){
        System.out.println(introdutionMessage);
        this.id = id;
        this.message = message;
    }

    /**
     * Method that print information about current object field's values
     * @return String with info about state of object fields
     */
    public String outCurrentObjectInfo(){
        String out = "id = " + id + "; message = '" + message + "'";
        System.out.println(out);
        return out;
    }
}
