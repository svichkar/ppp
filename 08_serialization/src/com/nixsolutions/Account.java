package com.nixsolutions;

import java.io.*;

/**
 * Created by sobolenko on 2/5/2016.
 */
public class Account implements Serializable {
    private Long id;
    private String name;
    private String role;

    public Account(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        //System.out.println(id + "" + name);
        String result = "";
        result = result.concat(id.toString()).concat(",").concat(name).concat(",").concat(role);
        out.writeObject(result);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        //System.out.println(in.readObject());
        String[] objects = in.readObject().toString().split(",");
        id = Long.parseLong(objects[0]);
        name = objects[1];
        role = objects[2];
    }
}
