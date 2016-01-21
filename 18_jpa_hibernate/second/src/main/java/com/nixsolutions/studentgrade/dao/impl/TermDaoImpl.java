package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.util.H2ConnectionManager;
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
    public boolean create(Term term) {

        String sql = "INSERT INTO term(term_name) VALUES ( ? )";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, term.getTermName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean update(Term term) {

        String sql = "UPDATE term SET term_name = ? WHERE term_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, term.getTermName());
            statement.setLong(2, term.getTermId());
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

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, term.getTermId());
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

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Term term = new Term();
                term.setTermId(rs.getLong("term_id"));
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
    public Term findById(Long id) {

        String sql = String.format("SELECT term_id, term_name FROM term WHERE term_id = %d", id);
        Term result = new Term();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setTermId(rs.getLong("term_id"));
                result.setTermName(rs.getString("term_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Term findByName(String termName) {

        String sql = String.format("SELECT term_id, term_name FROM term WHERE term_name = '%s'", termName);
        Term result = new Term();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setTermId(rs.getLong("term_id"));
                result.setTermName(rs.getString("term_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
