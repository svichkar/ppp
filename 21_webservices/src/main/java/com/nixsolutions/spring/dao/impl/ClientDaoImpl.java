package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.ClientDAO;
import com.nixsolutions.spring.entity.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("clientDAO")
@Transactional
public class ClientDaoImpl implements ClientDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Client entity) {
        sessionFactory.getCurrentSession().save("client", entity);
    }


    @Override
    public void update(Client entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("client", entity);
    }

    @Override
    public void delete(Client entity) {
        sessionFactory.getCurrentSession().delete("client", entity);
    }

    @Override
    public Client findByID(Long id) {
        return (Client) sessionFactory.getCurrentSession().byId(Client.class).load(id);
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) sessionFactory.getCurrentSession().createCriteria(Client.class).list();
    }
}
