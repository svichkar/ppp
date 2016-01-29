package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.ClientDAO;
import com.nixsolutions.servicestation.entity.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class ClientDAOImpl extends GenericAbstractDAO<Client> implements ClientDAO {
    public Set<Client> findClientsUsers() {
        Set<Client> ucSet;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Client.class, "c");
            criteria.createAlias("c.user", "user");
            criteria.createAlias("user.role", "role");
            ucSet = new HashSet<Client>(criteria.list());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return ucSet;
    }

}
