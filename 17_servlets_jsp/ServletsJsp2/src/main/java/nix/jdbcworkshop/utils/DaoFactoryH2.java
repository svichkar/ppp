/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.utils;

import nix.jdbcworkshop.dao.impl.CarDaoH2;
import nix.jdbcworkshop.dao.impl.CarOrderDaoH2;
import nix.jdbcworkshop.dao.impl.CarOrderStatusDaoH2;
import nix.jdbcworkshop.dao.impl.CarTypeDaoH2;
import nix.jdbcworkshop.dao.impl.ClientDaoH2;
import nix.jdbcworkshop.dao.impl.EmployeeCarOrderDaoH2;
import nix.jdbcworkshop.dao.impl.EmployeeCategoryDaoH2;
import nix.jdbcworkshop.dao.impl.EmployeeDaoH2;
import nix.jdbcworkshop.dao.impl.WebRoleDaoH2;
import nix.jdbcworkshop.dao.impl.WebUserDaoH2;

/**
 *
 * @author mednorcom
 */
public class DaoFactoryH2 {

    public static CarDaoH2 getCarDaoH2() {
        return new CarDaoH2();
    }

    public static CarOrderDaoH2 getCarOrderDaoH2() {
        return new CarOrderDaoH2();
    }

    public static CarOrderStatusDaoH2 getCarOrderStatusDaoH2() {
        return new CarOrderStatusDaoH2();
    }

    public static CarTypeDaoH2 getCarTypeDaoH2() {
        return new CarTypeDaoH2();
    }

    public static ClientDaoH2 getClientDaoH2() {
        return new ClientDaoH2();
    }

    public static EmployeeCarOrderDaoH2 getEmployeeCarOrderDaoH2() {
        return new EmployeeCarOrderDaoH2();
    }

    public static EmployeeCategoryDaoH2 getEmployeeCategoryDaoH2() {
        return new EmployeeCategoryDaoH2();
    }

    public static EmployeeDaoH2 getEmployeeDaoH2() {
        return new EmployeeDaoH2();
    }
    
    public static WebUserDaoH2 getWebUserDaoH2() {
        return new WebUserDaoH2();
    }
    
    public static WebRoleDaoH2 getWebRoleDaoH2() {
        return new WebRoleDaoH2();
    }

}
