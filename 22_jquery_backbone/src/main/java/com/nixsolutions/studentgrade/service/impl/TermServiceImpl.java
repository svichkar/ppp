package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */
@Service
@Transactional
public class TermServiceImpl implements TermService {

    private TermDao termDao;

    @Autowired
    @Qualifier("termDao")
    public void setTermDao(TermDao termDao) {
        this.termDao = termDao;
    }

    @Override
    public void create(Term term) {

        termDao.create(term);
    }

    @Override
    public void update(Term term) {

        termDao.update(term);
    }

    @Override
    public void delete(Term term) {

        termDao.delete(term);
    }

    @Override
    public List<Term> findAll() {

        return termDao.findAll();
    }

    @Override
    public Term findById(Long id) {

        return termDao.findById(id);
    }

    @Override
    public Term findByName(String termName) {

        return termDao.findByName(termName);
    }
}
