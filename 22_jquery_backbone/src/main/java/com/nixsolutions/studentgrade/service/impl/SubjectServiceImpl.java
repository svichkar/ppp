package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.model.Subject;
import com.nixsolutions.studentgrade.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao;

    @Autowired
    @Qualifier("subjectDao")
    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public void create(Subject subject) {

        subjectDao.create(subject);
    }

    @Override
    public void update(Subject subject) {

        subjectDao.update(subject);
    }

    @Override
    public void delete(Subject subject) {

        subjectDao.delete(subject);
    }

    @Override
    public List<Subject> findAll() {

        return subjectDao.findAll();
    }

    @Override
    public Subject findById(Long id) {

        return subjectDao.findById(id);
    }

    @Override
    public Subject findByName(String subjectName) {

        return subjectDao.findByName(subjectName);
    }

    @Override
    public Subject findByNameAndTermId(String subjectName, Long termId) {

        return subjectDao.findByNameAndTermId(subjectName, termId);
    }

    @Override
    public List<Subject> findByTermId(Long termId) {

        return subjectDao.findByTermId(termId);
    }

    @Override
    public List<Subject> findByTermName(String termName) {

        return subjectDao.findByTermName(termName);
    }

    @Override
    public List<Subject> findByNameAndTerm(String subjectName, String termName) {

        List<Subject> result = new ArrayList<>();

        if (subjectName != null && !subjectName.isEmpty()) {

            if (termName != null && !termName.isEmpty()) {
                Subject s = subjectDao.findByNameAndTerm(subjectName, termName);
                if (s != null)
                    result.add(s);

            } else {

                result.add(subjectDao.findByName(subjectName));
            }

        } else {
            if (termName != null && !termName.isEmpty()) {

                result = subjectDao.findByTermName(termName);
            }
        }

        return result;
    }
}
