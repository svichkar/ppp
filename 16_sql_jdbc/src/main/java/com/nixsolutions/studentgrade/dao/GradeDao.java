package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Grade;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface GradeDao {

    public boolean create(Grade grade);

    public int update(Grade grade);

    public int delete(Grade grade);

    public List<Grade> findAll();

    public Grade findById(int id);
}
