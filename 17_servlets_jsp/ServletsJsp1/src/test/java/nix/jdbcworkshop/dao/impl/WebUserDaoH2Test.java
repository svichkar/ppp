/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.WebUser;
import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author mednorcom
 */
public class WebUserDaoH2Test extends AbstractDaoH2Test {

    private WebUserDaoH2 webUserDaoInstance;

    public WebUserDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(new IDataSet[]{
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.web.role.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.web.user.initial")))
        });
    }

    public void setUp() throws Exception {
        super.setUp();
        webUserDaoInstance = new WebUserDaoH2();
    }

    /**
     * Test of create method, of class WebUserDaoH2.
     */
    public void testCreate() throws Exception {
        WebUser webUser = new WebUser(null, "Name6", "Pass6", (short) 2);
        webUserDaoInstance.create(webUser);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("web_user");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.web.user.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("web_user");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(webUser.getWebUserId());
    }

    /**
     * Test of update method, of class WebUserDaoH2.
     */
    public void testUpdate() throws Exception {
        WebUser webUser = new WebUser(null, "Name6", "Pass6", (short) 2);
        webUserDaoInstance.create(webUser);
        webUser.setWebRoleId((short) 1);
        webUserDaoInstance.update(webUser);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("web_user");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.web.user.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("web_user");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of delete method, of class WebUserDaoH2.
     */
    public void testDelete() throws Exception {
        WebUser webUser = new WebUser(null, "Name6", "Pass6", (short) 2);
        webUserDaoInstance.create(webUser);
        webUserDaoInstance.delete(webUser);
        webUserDaoInstance.delete(new WebUser((long) 5, null, null, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("web_user");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.web.user.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("web_user");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class WebUserDaoH2.
     */
    public void testFindWebUserById() {
        WebUser webUser = webUserDaoInstance.findWebUserById(5);
        assertEquals(new Long(5), webUser.getWebUserId());
        assertEquals("Name5", webUser.getWebUserLogin());
        assertEquals("Pass5", webUser.getWebUserPassword());
        assertEquals(new Short((short) 1), webUser.getWebRoleId());
    }

    public void testFindWebUserByName() {
        WebUser webUser = webUserDaoInstance.findWebUserByLogin("Name5");
        assertEquals(new Long(5), webUser.getWebUserId());
        assertEquals("Name5", webUser.getWebUserLogin());
        assertEquals("Pass5", webUser.getWebUserPassword());
        assertEquals(new Short((short) 1), webUser.getWebRoleId());
    }

    public void testGetWebUserList() {
        List<WebUser> webUserList = webUserDaoInstance.getWebUserList();
        assertEquals(5, webUserList.size());
    }

    public void testGetWebUserListLimit() {
        List<WebUser> webUserListLimit = webUserDaoInstance.getWebUserList(4);
        assertEquals(4, webUserListLimit.size());
        assertEquals(new Long(1), webUserListLimit.get(0).getWebUserId());
        assertEquals(new Long(4), webUserListLimit.get(3).getWebUserId());
    }

    public void testGetWebUserListOffset() {
        List<WebUser> webUserListOffset = webUserDaoInstance.getWebUserList(1, 4);
        assertEquals(4, webUserListOffset.size());
        assertEquals(new Long(2), webUserListOffset.get(0).getWebUserId());
        assertEquals(new Long(5), webUserListOffset.get(3).getWebUserId());
    }

}
