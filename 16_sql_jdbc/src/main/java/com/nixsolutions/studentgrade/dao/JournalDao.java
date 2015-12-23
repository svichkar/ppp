package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Journal;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface JournalDao {

    public boolean create(Journal journal);

    public int update(Journal journal);

    public int delete(Journal journal);

    public List<Journal> findAll();

    public Journal findById(int id);
}
