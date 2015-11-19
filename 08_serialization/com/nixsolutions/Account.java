package com.nixsolutions;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Rybkinrolla on 19.11.2015.
 */
public class Account implements Externalizable {
    private long id;
    private String name;
    private String role;

    public Account() {
        this.id = 0;
        this.name = "No name";
        this.name = "No role";
    }

    public Account(long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String writeAccountToString() {
        return id + ":" + name + ":" + role;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.writeAccountToString());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String[] arrOfAccount = ((String) in.readObject()).split(":");
        id = Long.parseLong(arrOfAccount[0]);
        name = arrOfAccount[1];
        role = arrOfAccount[2];
    }

}
