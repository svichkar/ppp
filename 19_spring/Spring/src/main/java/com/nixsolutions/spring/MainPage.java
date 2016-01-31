package com.nixsolutions.spring;



import com.nixsolutions.spring.dao.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kozlovskij on 12/29/2015.
 */

@WebServlet("/main")
public class MainPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context/spring-base-context.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "<title>login page</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p>" + userDAO.findAll().size() + "</p>" +
                    "</body>\n" +
                    "</html>");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
