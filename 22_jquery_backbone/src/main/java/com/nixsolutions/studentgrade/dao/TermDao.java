package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Term;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */
public interface TermDao {

    void create(Term term);

    void update(Term term);

    void delete(Term term);

    List<Term> findAll();

    Term findById(Long id);

    Term findByName(String termName);
}
