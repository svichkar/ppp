/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.WebRole;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author mednorcom
 */
public class WebRoleDaoH2Test extends AbstractDaoH2Test {

    private WebRoleDaoH2 webRoleDaoInstance;

    public WebRoleDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(
                getClass().getClassLoader().getResourceAsStream(dbunitConfig
                        .getString("dbunit.web.role.initial")));
    }

    public void setUp() throws Exception {
        super.setUp();
        webRoleDaoInstance = new WebRoleDaoH2();
    }

    /**
     * Test of create method, of class WebRoleDaoH2.
     */
    public void testCreate() throws Exception {
        WebRole webRole = new WebRole(null, "manager");
        webRoleDaoInstance.create(webRole);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("web_role");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.web.role.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("web_role");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(webRole.getWebRoleId());
    }

    /**
     * Test of update method, of class WebRoleDaoH2.
     */
    public void testUpdate() throws Exception {
        WebRole webRole = new WebRole(null, "manager");
        webRoleDaoInstance.create(webRole);
        webRole.setWebRoleName("limited");
        webRoleDaoInstance.update(webRole);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("web_role");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.web.role.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("web_role");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        //webRoleDaoInstance.delete(webRole);
    }

    /**
     * Test of delete method, of class WebRoleDaoH2.
     */
    public void testDelete() throws Exception {
        WebRole webRole = new WebRole(null, "manager");
        webRoleDaoInstance.create(webRole);
        webRoleDaoInstance.delete(webRole);
        webRoleDaoInstance.delete(new WebRole((short) 2, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("web_role");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.web.role.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("web_role");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class WebRoleDaoH2.
     */
    public void testFindWebRoleById() {
        WebRole webRole = webRoleDaoInstance.findWebRoleById((short)2);
        assertEquals(new Short((short) 2), webRole.getWebRoleId());
        assertEquals("admin", webRole.getWebRoleName());
    }

    public void testGetWebRoleList() {
        List<WebRole> webRoleList 
                = webRoleDaoInstance.getWebRoleList();
        assertEquals(2, webRoleList.size());
    }

    public void testGetWebRoleListLimit() {
        List<WebRole> webRoleListLimit 
                = webRoleDaoInstance.getWebRoleList(1);
        assertEquals(1, webRoleListLimit.size());
        assertEquals(new Short((short) 1), webRoleListLimit.get(0).getWebRoleId());
    }

    public void testGetWebRoleListOffset() {
        List<WebRole> webRoleListOffset 
                = webRoleDaoInstance.getWebRoleList(1, 1);
        assertEquals(1, webRoleListOffset.size());
        assertEquals(new Short((short) 2), webRoleListOffset.get(0)
                .getWebRoleId());
    }

}
