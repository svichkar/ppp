package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Term;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface TermDao {

    public Term create(Term status);

    public boolean update(Term term);

    public boolean delete(Term term);

    public List<Term> findAll();

    public Term findById(int id);
}
