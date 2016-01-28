package com.nixsolutions.hibernate;


import com.nixsolutions.hibernate.dao.AuthorDAO;
import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.util.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;




/**
 * Created by kozlovskij on 1/28/2016.
 */
public class Test {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AuthorDAO authorDAO = (AuthorDAO) context.getBean("authorDAO");
        Author author = new Author();
        author.setAuthorFirstName("test");
        author.setAuthorLastName("test");
        authorDAO.create(author);
        System.out.println(author.getAuthorId());
    }
}
