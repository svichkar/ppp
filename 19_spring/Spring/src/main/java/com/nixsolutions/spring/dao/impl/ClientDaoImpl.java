package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.ClientDAO;
import com.nixsolutions.spring.entity.Cell;
import com.nixsolutions.spring.entity.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */@Repository("clientDAO")
@Transactional
public class ClientDaoImpl implements ClientDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Client entity) {
        try {
            sessionFactory.getCurrentSession().save("client", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Client entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("client", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Client entity) {
        try {
            sessionFactory.getCurrentSession().delete("client", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client findByID(Long id) {
        Client entity = null;
        try {
            entity = (Client) sessionFactory.getCurrentSession().byId(Client.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Client> findAll() {
        List<Client> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Cell.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
