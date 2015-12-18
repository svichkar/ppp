package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.entity.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class GradeDaoImpl implements GradeDao {


    @Override
    public boolean create() {
        return false;
    }

    @Override
    public int update(Grade grade) {
        return 0;
    }

    @Override
    public boolean delete(Grade grade) {
        return false;
    }

    @Override
    public List<Grade> findAll() {

        String sql = "SELECT * FROM grade;";
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
    public Grade findById(int i) {
        return null;
    }
}
