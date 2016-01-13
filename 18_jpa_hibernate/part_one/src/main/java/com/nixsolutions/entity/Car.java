package com.nixsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
@Entity
public class Car implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "serial_VIN", length = 100, nullable = false)
    private String serialVIN;
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_type_id", referencedColumnName = "car_type_id")
    private CarType carType;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    public Car() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getSerialVIN() {
        return serialVIN;
    }

    public void setSerialVIN(String serialVIN) {
        this.serialVIN = serialVIN;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
