package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.util.H2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by konstantin on 1/16/2016.
 */
public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);
    public static void main (String[] args) {

        DaoFactory daoFactory = new DaoFactory();
        SubjectDao dao = daoFactory.getSubjectDao();
        TermDao termDao = daoFactory.getTermDao();

        daoFactory.getTermDao().findAll();
        daoFactory.getTermDao().create(new Term("fifth"));

        String subjectId = "10";
        String subjectName = "Theory of everything";
        String termName = "first";
        String operation = "update";

        Subject subject = new Subject();
        subject.setSubjectId(Long.valueOf(subjectId));
        subject.setSubjectName(subjectName);
        subject.setTermId(termDao.findByName(termName).getTermId());

        try (Connection connection = H2ConnectionManager.getConnection();) {
            Statement statement = connection.createStatement();
            /*
            statement.executeUpdate("DROP TABLE IF EXISTS student_group;");
            LOG.info("Table 'student_group' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS status;");
            LOG.info("Table 'status' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS term;");
            LOG.info("Table 'term' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS grade;");
            LOG.info("Table 'grade' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS subject;");
            LOG.info("Table 'subject' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS student;");
            LOG.info("Table 'student' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS journal;");
            LOG.info("Table 'journal' has been dropped.");
            LOG.info("All tables dropped from database.");
            */

            /*
            statement.executeUpdate("CREATE TABLE student_group(group_id BIGINT IDENTITY PRIMARY KEY, group_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'student_group' has been created.");
            statement.executeUpdate("CREATE TABLE status (status_id TINYINT IDENTITY PRIMARY KEY, status_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'status' has been created.");
            statement.executeUpdate("CREATE TABLE term(term_id BIGINT IDENTITY PRIMARY KEY, term_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'term' has been created.");
            statement.executeUpdate("CREATE TABLE student (student_id BIGINT IDENTITY PRIMARY KEY, " +
                    "first_name VARCHAR(256) NOT NULL, last_name VARCHAR(256) NOT NULL, " +
                    "admission_date DATE NOT NULL, group_id BIGINT NOT NULL REFERENCES student_group(group_id), " +
                    "status_id TINYINT NOT NULL REFERENCES status(status_id), term_id BIGINT REFERENCES term(term_id));");
            LOG.info("Table 'student' has been created.");
            statement.executeUpdate("CREATE INDEX last_name_indx ON student(last_name);");
            LOG.info("Index 'last_name_indx' on 'last_name' column of 'student' table  has been created.");
            statement.executeUpdate("CREATE TABLE grade(grade_id TINYINT IDENTITY PRIMARY KEY, grade_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'grade' has been created.");
            statement.executeUpdate("CREATE TABLE subject(subject_id BIGINT IDENTITY PRIMARY KEY, " +
                    "subject_name VARCHAR(256) NOT NULL, term_id BIGINT REFERENCES term(term_id));");
            LOG.info("Table 'subject' has been created.");
            statement.executeUpdate("CREATE INDEX subject_indx ON subject(subject_name);");
            LOG.info("Index 'subject_indx' on 'subject_name' column in 'subject' table has been created.");
            statement.executeUpdate("CREATE TABLE journal(journal_id BIGINT IDENTITY PRIMARY KEY, " +
                    "student_id BIGINT REFERENCES student(student_id), subject_id BIGINT REFERENCES subject(subject_id), " +
                    "grade_id TINYINT REFERENCES grade(grade_id));");
            LOG.info("Table 'journal' has been created.");

            LOG.info("All tables successfully created.");
            */


        }
        catch (SQLException e) {}
    }
}
