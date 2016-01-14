/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.CarOrder;

/**
 *
 * @author mednorcom
 */
public interface CarOrderDao {
    public void create(CarOrder carOrder);
    public void update(CarOrder carOrder);
    public void delete(CarOrder carOrder);
    public CarOrder findCarOrderById(long carOrderId);
    public List<CarOrder> getCarOrderList();
    public List<CarOrder> getCarOrderList(int limit);
    public List<CarOrder> getCarOrderList(int offset, int limit);
}
