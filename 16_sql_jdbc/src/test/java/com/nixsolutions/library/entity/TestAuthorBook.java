package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * Created by kozlovskij on 12/24/2015.
 */

public class TestAuthorBook extends DBTestCase {
    public static Logger LOGGER = LogManager.getLogger(DBTestCase.class.getName());
    private IDatabaseConnection connection;
    private IDataSet dataSet;
    private DaoFactory daoFactory;
    private String xmlPath;

    @Override
    protected IDataSet getDataSet() throws Exception {
        FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder().setMetaDataSet(dataSet);
        return null;
    }
}
