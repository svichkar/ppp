package com.nixsolutions.hibernate;

import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by kozlovskij on 1/13/2016.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Role role = new Role();
        role.setRoleName("Admin");

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();


        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Role");
        List <Role> list = query.list();
        transaction.commit();

        for (int i = 0; i < list.size(); i++) {
            role =  list.get(i);
            System.out.println(role.getRoleId() + "/" + role.getRoleName());
            List <User> users = role.getUsers();
            for (int j = 0; j <users.size() ; j++) {
                User user = users.get(j);
                System.out.println(user.getUserId() + "/" + user.getLogin());
            }
        }
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Role newRole = (Role) session.get(Role.class, new Long(33));
        transaction.commit();
        System.out.println(newRole.getRoleId() + newRole.getRoleName() + newRole.getUsers().get(0).getLogin());
        HibernateUtil.closeSessionFactory();

    }
}
