/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.Car;

/**
 *
 * @author mednorcom
 */
public interface CarDao {
    public void create(Car car);
    public void update(Car car);
    public void delete(Car car);
    public Car findCarById(long carId);
    public List<Car> getCarList();
    public List<Car> getCarList(int limit);
    public List<Car> getCarList(int offset, int limit);
}
