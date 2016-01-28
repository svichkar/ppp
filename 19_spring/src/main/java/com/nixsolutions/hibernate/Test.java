package com.nixsolutions.hibernate;


import com.nixsolutions.hibernate.dao.AuthorDAO;
import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.util.SpringConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;


/**
 * Created by kozlovskij on 1/28/2016.
 */
public class Test {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AuthorDAO authorDAO = (AuthorDAO) context.getBean("authorDAO");
        System.out.println(authorDAO.findByID(1L).authorFullName());
        context.registerShutdownHook();
    }
}
