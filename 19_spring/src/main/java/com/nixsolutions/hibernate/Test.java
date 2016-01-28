package com.nixsolutions.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kozlovskij on 1/28/2016.
 */
public class Test {
    public static ApplicationContext context;
    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("com.nixsolutions.hibernate.springcfg.xml");
    }
}
