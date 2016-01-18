package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.util.H2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StudentGroupDaoImpl implements StudentGroupDao {

    private static final Logger LOG = LogManager.getLogger(StudentGroupDaoImpl.class);

    @Override
    public boolean create(StudentGroup group) {

        String sql = "INSERT INTO student_group (group_name) VALUES ( ? )";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, group.getGroupName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean update(StudentGroup group) {

        String sql = "UPDATE student_group SET group_name = ? WHERE group_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, group.getGroupName());
            statement.setLong(2, group.getGroupId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(StudentGroup group) {

        String sql = "DELETE FROM student_group WHERE group_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, group.getGroupId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public List<StudentGroup> findAll() {

        String sql = "SELECT * FROM student_group";
        List<StudentGroup> list = new ArrayList<>();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                StudentGroup group = new StudentGroup();
                group.setGroupId(rs.getLong("group_id"));
                group.setGroupName(rs.getString("group_name"));
                list.add(group);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public StudentGroup findById(Long id) {

        String sql = String.format("SELECT group_id, group_name FROM student_group WHERE group_id = %d", id);
        StudentGroup result = new StudentGroup();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                result.setGroupId(rs.getLong("group_id"));
                result.setGroupName(rs.getString("group_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public StudentGroup findByName(String groupName) {

        String sql = String.format("SELECT group_id, group_name FROM student_group WHERE group_name = '%s'", groupName);
        StudentGroup result = new StudentGroup();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                result.setGroupId(rs.getLong("group_id"));
                result.setGroupName(rs.getString("group_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
