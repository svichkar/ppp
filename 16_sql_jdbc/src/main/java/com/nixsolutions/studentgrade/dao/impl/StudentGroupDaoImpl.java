package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StudentGroupDaoImpl implements StudentGroupDao {

    @Override
    public boolean create(StudentGroup group) {

        String sql = "INSERT INTO student_group (group_id, group_name) VALUES ( ? , ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, group.getGroupId());
            statement.setString(2, group.getGroupName());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(StudentGroup group, StudentGroup newGroup) {

        String sql = "UPDATE student_group SET group_id = ?, group_name = ? WHERE group_id = ? and group_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newGroup.getGroupId());
            statement.setString(2, newGroup.getGroupName());
            statement.setInt(3, group.getGroupId());
            statement.setString(4, group.getGroupName());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(StudentGroup group) {

        String sql = "DELETE FROM student_group WHERE group_id = ? and group_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, group.getGroupId());
            statement.setString(2, group.getGroupName());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<StudentGroup> findAll() {

        String sql = "SELECT * FROM student_group";
        List<StudentGroup> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                StudentGroup group = new StudentGroup();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                list.add(group);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public StudentGroup findById(int id) {

        String sql = String.format("SELECT group_id, group_name FROM student_group WHERE group_id = %d", id);
        StudentGroup result = new StudentGroup();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setGroupId(rs.getInt("group_id"));
                result.setGroupName(rs.getString("group_name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
