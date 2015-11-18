package com.nixsolutions;

import java.io.Serializable;

/**
 * Created by kozlovskij on 11/18/2015.
 */
public class TransferObject implements Serializable {

    private Long id;
    private String message;

    public TransferObject(Long id, String message ) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransferObject{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
