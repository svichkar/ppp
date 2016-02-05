package com.nixsolutions;

import java.io.Serializable;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 12352354123L;

    private int id;
    private String ownerName;
    private double cost;

    public Ticket(int id, String ownerName, double cost) {
        this.id = id;
        this.ownerName = ownerName;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", cost=" + cost +
                '}';
    }

}
