package com.manetskiy;


import java.io.*;

public class Account implements Externalizable {
    private long userId;
    private String name;
    private String role;

    public Account(long userId, String name, String role) {
        setUserId(userId);
        setName(name);
        setRole(role);
    }

    public Account() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getUserId() + ";" + getName() + ";" + getRole());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String s = (String) in.readObject();
        String[] data = s.split(";");
        setUserId(Long.valueOf(data[0]));
        setName(data[1]);
        setRole(data[2]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: ").append(getUserId());
        sb.append(" User name: ").append(getName());
        sb.append(" User role: ").append(getRole());
        return sb.toString();
    }
}
