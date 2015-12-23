package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.CellDAO;
import com.nixsolutions.library.entity.Cell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 12/23/2015.
 */
public class CellDaoImpl implements CellDAO{
    public static Logger LOGGER = LogManager.getLogger(CellDaoImpl.class.getName());

    @Override
    public Cell create(Cell entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO cell (name) VALUES ('" + entity.getName() + "');");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void update(Cell entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE cell SET name='" + entity.getName() + "' WHERE cell_id='" +
                    entity.getCellId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Cell entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM cell WHERE cell_id='" + entity.getCellId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Cell findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cell WHERE cell_id = '" + id + "';");
            resultSet.next();
            Cell entity = new Cell(resultSet.getInt("cell_id"), resultSet.getString("name"));
            return entity;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Cell> findAll() {
        List<Cell> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cell;");
            while (resultSet.next())
                list.add(new Cell(resultSet.getInt("cell_id"), resultSet.getString("name")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
