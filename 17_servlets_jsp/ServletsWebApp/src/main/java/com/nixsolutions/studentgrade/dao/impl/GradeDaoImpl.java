package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class GradeDaoImpl implements GradeDao {

    @Override
    public boolean create(Grade grade) {

        String sql = "INSERT INTO grade(grade_id, grade_name) VALUES ( ? , ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, grade.getGradeId());
            statement.setString(2, grade.getGradeName());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Grade grade, Grade newGrade) {

        String sql = "UPDATE grade SET grade_id = ?, grade_name = ? WHERE grade_id = ? AND grade_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newGrade.getGradeId());
            statement.setString(2, newGrade.getGradeName());
            statement.setInt(3, grade.getGradeId());
            statement.setString(4, grade.getGradeName());
            return statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Grade grade) {

        String sql = "DELETE FROM grade WHERE grade_id = ? AND grade_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, grade.getGradeId());
            statement.setString(2, grade.getGradeName());
            return statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Grade> findAll() {

        String sql = "SELECT * FROM grade";
        List<Grade> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGradeId(rs.getInt("grade_id"));
                grade.setGradeName(rs.getString("grade_name"));
                list.add(grade);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Grade findById(int id) {

        String sql = String.format("SELECT grade_id, grade_name FROM grade WHERE grade_id = %d", id);
        Grade result = new Grade();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setGradeId(rs.getInt("grade_id"));
                result.setGradeName(rs.getString("grade_name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
