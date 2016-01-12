/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import nix.jdbcworkshop.entities.CarType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mednorcom
 */
public class CarTypeDaoH2Test {
    
    public CarTypeDaoH2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class CarTypeDaoH2.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        CarType carType = null;
        CarTypeDaoH2 instance = new CarTypeDaoH2();
        instance.create(carType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class CarTypeDaoH2.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        CarType carType = null;
        CarTypeDaoH2 instance = new CarTypeDaoH2();
        instance.update(carType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class CarTypeDaoH2.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        CarType carType = null;
        CarTypeDaoH2 instance = new CarTypeDaoH2();
        instance.delete(carType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCarById method, of class CarTypeDaoH2.
     */
    @Test
    public void testFindCarById() {
        System.out.println("findCarById");
        long carTypeId = 0L;
        CarTypeDaoH2 instance = new CarTypeDaoH2();
        CarType expResult = null;
        CarType result = instance.findCarById(carTypeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
