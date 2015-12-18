package com.nixsolutions.studentgrade.apps;

import com.nixsolutions.studentgrade.dao.impl.CustomConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Create {

    private static Connection connection;

    public static void main (String args[]) {

        try {
            Class.forName("org.h2.Driver");

            connection = CustomConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            //statement.executeUpdate("CREATE TABLE student_group(group_id BIGINT PRIMARY KEY, group_name VARCHAR(256) NOT NULL);");

            ResultSet resultSet = statement.executeQuery("select * from student_group;");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
