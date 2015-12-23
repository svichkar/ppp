package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class TermDaoImpl implements TermDao {

    @Override
    public boolean create(Term term) {

        String sql = "INSERT INTO term(term_id, term_name) VALUES ( ? , ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, term.getTermId());
            statement.setString(2, term.getTermName());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Term term) {

        String sql = "UPDATE term SET term_name = ? WHERE term_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, term.getTermName());
            statement.setInt(2, term.getTermId());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Term term) {

        String sql = "DELETE FROM term WHERE term_id = ? AND term_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, term.getTermId());
            statement.setString(2, term.getTermName());
            return statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
