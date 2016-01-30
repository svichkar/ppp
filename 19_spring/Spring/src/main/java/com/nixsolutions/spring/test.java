package com.nixsolutions.spring;

import com.nixsolutions.spring.dao.AuthorDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Serko on 30.01.2016.
 */
public class test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AuthorDAO authorDAO = (AuthorDAO) context.getBean("authorDAO");
        System.out.println(authorDAO.findAll().size());
    }
}
