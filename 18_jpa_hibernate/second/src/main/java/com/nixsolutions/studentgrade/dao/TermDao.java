package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Term;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface TermDao {

    public void create(Term term);

    public void update(Term term);

    public void delete(Term term);

    public List<Term> findAll();

    public Term findById(Long id);

    public Term findByName(String termName);
}
