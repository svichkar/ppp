package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.UserDAO;
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
public class TestUser extends DBUnitConfig {
    private User user;

    public TestUser(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/User/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        user = new User("Vasya", "555",2);
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.create(user);
        IDataSet expected = new FlatXmlDataFileLoader().load("/User/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("user"),
                expected.getTableMetaData("user").getColumns());
        Assertion.assertEquals(expected.getTable("user"), filteredTable);
    }

    public void testUpdate() throws Exception {
        user = new User(1, "Admin", "777", 1);
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.update(user);
        IDataSet expected = new FlatXmlDataFileLoader().load("/User/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("user"),
                expected.getTableMetaData("user").getColumns());
        Assertion.assertEquals(expected.getTable("user"), filteredTable);
    }

    public void testDelete() throws Exception {
        user = new User(1, "admin", "123", 1);
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.delete(user);
        IDataSet expected = new FlatXmlDataFileLoader().load("/User/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("user"),
                expected.getTableMetaData("user").getColumns());
        Assertion.assertEquals(expected.getTable("user"), filteredTable);
    }

    public void testFindById() throws Exception {
        UserDAO userDAO = daoFactory.getUserDAO();
        user = userDAO.findByID(1);

        Assert.assertEquals(new Integer(1), user.getUserId());
        Assert.assertEquals("admin", user.getLogin());
        Assert.assertEquals("123", user.getPassword());
        Assert.assertEquals(new Integer(1), user.getRoleId());
    }

    public void testFindAll() throws Exception {
        UserDAO userDAO = daoFactory.getUserDAO();
        List<User> userList;
        userList = userDAO.findAll();

        Assert.assertEquals(2, userList.size());

        Assert.assertEquals(new Integer(1), userList.get(0).getUserId());
        Assert.assertEquals("admin", userList.get(0).getLogin());
        Assert.assertEquals("123", userList.get(0).getPassword());
        Assert.assertEquals(new Integer(1), userList.get(0).getRoleId());

        Assert.assertEquals(new Integer(2), userList.get(1).getUserId());
        Assert.assertEquals("petya", userList.get(1).getLogin());
        Assert.assertEquals("321", userList.get(1).getPassword());
        Assert.assertEquals(new Integer(2), userList.get(1).getRoleId());
    }
}
