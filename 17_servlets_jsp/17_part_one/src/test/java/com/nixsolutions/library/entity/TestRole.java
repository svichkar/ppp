package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.List;

/**
 * Created by kozlovskij on 12/28/2015.
 */
/*public class TestRole extends DBUnitConfig {
    private Role role;

    public TestRole(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Role/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        role = new Role("Client");
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        roleDAO.create(role);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Role/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("role"),
                expected.getTableMetaData("role").getColumns());
        Assertion.assertEquals(expected.getTable("role"), filteredTable);
    }

    public void testUpdate() throws Exception {
        role = new Role(1, "Admin");
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        roleDAO.update(role);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Role/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("role"),
                expected.getTableMetaData("role").getColumns());
        Assertion.assertEquals(expected.getTable("role"), filteredTable);
    }

    public void testDelete() throws Exception {
        role = new Role(1, "Admin");
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        roleDAO.delete(role);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Role/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("role"),
                expected.getTableMetaData("role").getColumns());
        Assertion.assertEquals(expected.getTable("role"), filteredTable);
    }

    public void testFindById() throws Exception {
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        role = roleDAO.findByID(1);

        Assert.assertEquals(new Integer(1), role.getRoleId());
        Assert.assertEquals("admin", role.getName());
    }

    public void testFindAll() throws Exception {
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        List<Role> roleList;
        roleList = roleDAO.findAll();

        Assert.assertEquals(2, roleList.size());

        Assert.assertEquals(new Integer(1), roleList.get(0).getRoleId());
        Assert.assertEquals("admin", roleList.get(0).getName());

        Assert.assertEquals(new Integer(2), roleList.get(1).getRoleId());
        Assert.assertEquals("customer", roleList.get(1).getName());
    }
}*/
