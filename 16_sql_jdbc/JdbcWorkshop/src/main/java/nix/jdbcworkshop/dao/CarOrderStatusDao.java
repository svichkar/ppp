/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.CarOrderStatus;

/**
 *
 * @author mednorcom
 */
public interface CarOrderStatusDao {

    public void create(CarOrderStatus carOrderStatus);

    public void update(CarOrderStatus carOrderStatus);

    public void delete(CarOrderStatus carOrderStatus);

    public CarOrderStatus findCarOrderStatusById(long carOrderStatusId);

    public List<CarOrderStatus> getCarOrderStatusList();

    public List<CarOrderStatus> getCarOrderStatusList(int limit);

    public List<CarOrderStatus> getCarOrderStatusList(int offset, int limit);
}
