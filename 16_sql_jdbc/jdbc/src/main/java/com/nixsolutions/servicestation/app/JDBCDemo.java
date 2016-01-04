package com.nixsolutions.servicestation.app;

import com.nixsolutions.servicestation.dao.impl.ImplCarTypeDAO;
import com.nixsolutions.servicestation.entity.CarType;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class JDBCDemo {
    public static void main(String[] args) {
        CarType ct = new CarType("Honda", "Accord");
        ImplCarTypeDAO implCarTypeDAO = new ImplCarTypeDAO();
        implCarTypeDAO.create(ct);
        ct = implCarTypeDAO.findById(1);
        ct.setBrand("Uazzz");
        implCarTypeDAO.update(ct);
        implCarTypeDAO.findAll();
        implCarTypeDAO.delete(ct);
    }
}
