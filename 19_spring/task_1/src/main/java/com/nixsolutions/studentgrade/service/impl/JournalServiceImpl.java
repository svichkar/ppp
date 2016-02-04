package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.model.Journal;
import com.nixsolutions.studentgrade.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by konstantin on 1/29/2016.
 */
@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    private JournalDao journalDao;

    @Autowired
    @Qualifier("journalDao")
    public void setJournalDao(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @Override
    public void create(Journal journal) {

        journalDao.create(journal);
    }

    @Override
    public void update(Journal journal) {

        journalDao.update(journal);
    }

    @Override
    public void delete(Journal journal) {

        journalDao.delete(journal);
    }

    @Override
    public List<Journal> findAll() {

        return journalDao.findAll();
    }

    @Override
    public Journal findById(Long id) {

        return journalDao.findById(id);
    }

    @Override
    public List<Journal> findByStudentAndTerm(Long studentId, Long termId) {

        return journalDao.findByStudentAndTerm(studentId, termId);
    }

    @Override
    public String getAverageScore(Long studentId, Long termId) {

        Long sum = 0L;
        List<Journal> list = findByStudentAndTerm(studentId, termId);

        if (!list.isEmpty()) {
            for (Journal j : list) {
                sum += j.getGrade().getGradeId();
            }

            Float score = 0f;
            if (list.size() != 0) {
                score = Float.valueOf(sum) / list.size();
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            return df.format(score);
        } else {
            return null;
        }
    }
}
