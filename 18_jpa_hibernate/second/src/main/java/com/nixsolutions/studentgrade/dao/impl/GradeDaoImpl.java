package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.util.H2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class GradeDaoImpl implements GradeDao {

    private static final Logger LOG = LogManager.getLogger(GradeDaoImpl.class);

    @Override
    public boolean create(Grade grade) {

        String sql = "INSERT INTO grade(grade_name) VALUES ( ? )";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, grade.getGradeName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean update(Grade grade) {

        String sql = "UPDATE grade SET grade_name = ? WHERE grade_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, grade.getGradeName());
            statement.setLong(2, grade.getGradeId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Grade grade) {

        String sql = "DELETE FROM grade WHERE grade_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, grade.getGradeId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public List<Grade> findAll() {

        String sql = "SELECT * FROM grade";
        List<Grade> list = new ArrayList<>();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGradeId(rs.getLong("grade_id"));
                grade.setGradeName(rs.getString("grade_name"));
                list.add(grade);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Grade findById(Long id) {

        String sql = String.format("SELECT grade_id, grade_name FROM grade WHERE grade_id = %d", id);
        Grade result = new Grade();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setGradeId(rs.getLong("grade_id"));
                result.setGradeName(rs.getString("grade_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Grade findByName(String gradeName) {

        String sql = String.format("SELECT grade_id, grade_name FROM grade WHERE grade_name = '%s'", gradeName);
        Grade result = new Grade();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setGradeId(rs.getLong("grade_id"));
                result.setGradeName(rs.getString("grade_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
