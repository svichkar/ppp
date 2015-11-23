/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searializationworkshop;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.management.InvalidAttributeValueException;

/**
 *
 * @author mednorcom
 */
public class Account implements Externalizable {

    private long id;
    private String name;
    private String role;
    public static final long serialVersionUID = 2L;

    public Account() {
    }

    public Account(long id, String name, String role) throws InvalidAttributeValueException {
        this.setId(id);
        this.setName(name);
        this.setRole(role);
    }

    public long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) throws InvalidAttributeValueException {
        if (!name.contains("|")) {
            this.name = name;
        } else {
            throw new InvalidAttributeValueException("Cannot contain '|'");
        }
    }

    public String getRole() {
        return role;
    }

    public final void setRole(String role) throws InvalidAttributeValueException {
        if (!name.contains("|")) {
            this.role = role;
        } else {
            throw new InvalidAttributeValueException("Cannot contain '|'");
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(String.valueOf(this.getId()) + "|" + this.getName() + "|" + this.getRole());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String[] rawInput = (String.valueOf(in.readObject())).split("\\|");
        this.setId(Integer.parseInt(rawInput[0]));
        try {
            this.setName(rawInput[1]);
            this.setRole(rawInput[2]);
        } catch (InvalidAttributeValueException ex) {
            throw new IOException(ex);
        }

    }

}
