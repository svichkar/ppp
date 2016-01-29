package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.CellDAO;
import com.nixsolutions.hibernate.entity.Cell;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("cellDAO")
@Transactional
public class CellDaoImpl implements CellDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Cell entity) {
        try {
            sessionFactory.getCurrentSession().save("cell", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Cell entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("cell", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Cell entity) {
        try {
            sessionFactory.getCurrentSession().delete("cell", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cell findByID(Long id) {
        Cell entity = null;
        try {
            entity = (Cell) sessionFactory.getCurrentSession().byId(Cell.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Cell> findAll() {
        List<Cell> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Cell.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
