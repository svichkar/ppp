/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.CarType;

/**
 *
 * @author mednorcom
 */
public interface CarTypeDao {
    public void create(CarType carType);
    public void update(CarType carType);
    public void delete(CarType carType);
    public CarType findCarById(long carTypeId);
}
