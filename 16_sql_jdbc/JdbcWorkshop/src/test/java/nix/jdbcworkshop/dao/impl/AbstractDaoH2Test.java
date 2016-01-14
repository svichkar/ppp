/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.operation.DatabaseOperation;

/**
 *
 * @author mednorcom
 */
public abstract class AbstractDaoH2Test extends JdbcBasedDBTestCase {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    protected Configuration dbunitConfig;

    public AbstractDaoH2Test() {
        super();
        try {
            dbunitConfig = new PropertiesConfiguration("dbunit.properties");
        } catch (ConfigurationException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        ConnectionManagerH2.getJdbcConfig().setProperty("jdbc.connection.string", 
                dbunitConfig.getString("dbunit.connection.string"));
        ConnectionManagerH2.getJdbcConfig().setProperty("jdbc.username", 
                dbunitConfig.getString("dbunit.username"));
        ConnectionManagerH2.getJdbcConfig().setProperty("jdbc.password", 
                dbunitConfig.getString("dbunit.password"));
    }

    @Override
    protected String getDriverClass() {
        return ConnectionManagerH2.getJdbcConfig().getString("jdbc.driver");
    }

    @Override
    protected String getConnectionUrl() {
        return ConnectionManagerH2.getJdbcConfig().getString("jdbc.connection.string");
    }

    @Override
    protected String getUsername() {
        return ConnectionManagerH2.getJdbcConfig().getString("jdbc.username");
    }

    @Override
    protected String getPassword() {
        return ConnectionManagerH2.getJdbcConfig().getString("jdbc.password");
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT; 
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL; 
    }

}
