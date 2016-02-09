package com.nixsolutions;

import java.io.IOException;
import java.io.Serializable;

/**
 * Represents the essence of account according to variant 2 of 
 * 08_serialization lab. Fields are concatenated to a string during
 * serialization.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DELIMITER = "\";\"";

    public Long id;
    public String name;
    public String role;

    public Account() {
        this.id = 0L;
        this.name = "";
        this.role = "";
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        String[] sa = stream.readUTF().split(DELIMITER);
        id = Long.valueOf(sa[0]);
        name = sa[1];
        role = sa[2];
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeUTF(id + DELIMITER + name + DELIMITER + role);
    }
}
