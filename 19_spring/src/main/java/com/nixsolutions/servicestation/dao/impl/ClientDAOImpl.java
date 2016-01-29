package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.ClientDAO;
import com.nixsolutions.servicestation.entity.Client;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Repository("clientDao")
public class ClientDAOImpl extends GenericAbstractDAO<Client> implements ClientDAO {
    public Set<Client> findClientsUsers() {
        Set<Client> ucSet;
        Criteria criteria = getCurrentSession().createCriteria(Client.class, "c");
        criteria.createAlias("c.user", "user");
        criteria.createAlias("user.role", "role");
        ucSet = new HashSet<Client>(criteria.list());
        return ucSet;
    }

}
