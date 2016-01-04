package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class TermDaoImpl implements TermDao {

    private static final Logger LOG = LogManager.getLogger(TermDaoImpl.class);

    @Override
    public Term create(Term term) {

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO term(term_id, term_name) VALUES ( ? , ? )";

        try {
            connection = M2ConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            statement.setInt(1, term.getTermId());
            statement.setString(2, term.getTermName());
            statement.executeUpdate();
            connection.commit();
            return term;
        } catch (SQLException e) {
            LOG.error(e);
            try {
                connection.rollback();
            } catch (SQLException error) {
                LOG.error(error);
            }
            return null;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
    }

    @Override
    public boolean update(Term term) {

        String sql = "UPDATE term SET term_name = ? WHERE term_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, term.getTermName());
            statement.setInt(2, term.getTermId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Term term) {

        String sql = "DELETE FROM term WHERE term_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, term.getTermId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public List<Term> findAll() {
        String sql = "SELECT * FROM term";
        List<Term> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Term term = new Term();
                term.setTermId(rs.getInt("term_id"));
                term.setTermName(rs.getString("term_name"));
                list.add(term);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Term findById(int id) {

        String sql = String.format("SELECT term_id, term_name FROM term WHERE term_id = %d", id);
        Term result = new Term();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setTermId(rs.getInt("term_id"));
                result.setTermName(rs.getString("term_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
