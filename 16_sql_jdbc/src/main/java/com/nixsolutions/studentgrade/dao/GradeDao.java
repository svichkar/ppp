package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Grade;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface GradeDao {

    //http://www.dokwork.ru/2014/02/daotalk.html

    public boolean create();

    public int update(Grade grade);

    public boolean delete(Grade grade);

    public List<Grade> findAll();

    public Grade findById(int i);
}
