package com.nixsolutions.app;

import org.hibernate.Session;

import com.nixsolutions.entity.Role;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Role admRole = new Role();
        admRole.setRole_name("Administrator");
        Role userRole = new Role();
        userRole.setRole_name("User");
        Role workerRole = new Role();
        workerRole.setRole_name("Worker");
        session.save(admRole);
        session.save(userRole);
        session.save(workerRole);
        
        session.getTransaction().commit();
    }
}
