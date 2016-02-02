package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.CellDAO;
import com.nixsolutions.spring.entity.Cell;
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
        sessionFactory.getCurrentSession().save("cell", entity);
    }

    @Override
    public void update(Cell entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("cell", entity);
    }

    @Override
    public void delete(Cell entity) {
        sessionFactory.getCurrentSession().delete("cell", entity);
    }

    @Override
    public Cell findByID(Long id) {
        return (Cell) sessionFactory.getCurrentSession().byId(Cell.class).load(id);
    }

    @Override
    public List<Cell> findAll() {
        return (List<Cell>) sessionFactory.getCurrentSession().createCriteria(Cell.class).list();
    }
}
