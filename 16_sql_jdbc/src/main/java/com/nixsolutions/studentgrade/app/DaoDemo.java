package com.nixsolutions.studentgrade.app;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/22/2015.
 */
public class DaoDemo {

    public static void main(String args[]) {

        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();

        GradeDao dao = daoFactory.getGradeDao();

        Grade grade = new Grade(22, "aaaaa");
        dao.create(grade);
        grade.setGradeName("null");
        dao.update(grade);
        dao.delete(new Grade(12, "another one"));

        List<Grade> list = new ArrayList<>();
        list = dao.findAll();

        dao.findById(9);

    }
}
