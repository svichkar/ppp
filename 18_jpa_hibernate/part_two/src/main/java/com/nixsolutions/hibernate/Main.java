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

        Session session = null;
        Transaction transaction = null;

        /*session = sessionFactory.getCurrentSession()
        transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();*/


        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Role");
        List <Role> list = query.list();
        transaction.commit();
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {

            Role role =  list.get(i);
            System.out.println("We have role: " + role.getRoleId() + "/" + role.getRoleName());
            System.out.print("This role have following users:");
            List <User> users = role.getUsers();
            for (int j = 0; j <users.size() ; j++) {
                User user = users.get(j);
                System.out.println(user.getUserId() + "/" + user.getLogin());
            }
        }
        HibernateUtil.closeSessionFactory();

    }
}
