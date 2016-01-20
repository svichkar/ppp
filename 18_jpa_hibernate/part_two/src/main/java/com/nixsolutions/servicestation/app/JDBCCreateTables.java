package com.nixsolutions.servicestation.app;

import com.nixsolutions.servicestation.util.HiberUtil;

/**
 * Created by rybkinrolla on 20.01.2016.
 */
public class JDBCCreateTables {

    public static void main(String[] args) throws ClassNotFoundException {
        HiberUtil.getSessionFactory();
        HiberUtil.closeSessionFactory();
    }
}
