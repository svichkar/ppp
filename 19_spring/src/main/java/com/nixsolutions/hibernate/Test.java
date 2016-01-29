package com.nixsolutions.hibernate;


import com.nixsolutions.hibernate.dao.AuthorDAO;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by kozlovskij on 1/28/2016.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("springcfg.xml");
        SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
        //Thread.sleep(1000L);
        AuthorDAO authorDAO = (AuthorDAO) context.getBean("authorDAO");
        System.out.println(authorDAO.findByID(1L).authorFullName());
    }
}
