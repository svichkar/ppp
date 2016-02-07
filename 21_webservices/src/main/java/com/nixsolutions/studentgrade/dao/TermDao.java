package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Term;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */
public interface TermDao {

    public void create(Term term);

    public void update(Term term);

    public void delete(Term term);

    public List<Term> findAll();

    public Term findById(Long id);

    public Term findByName(String termName);
}
