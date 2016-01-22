package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by svichkar on 1/13/2016.
 */
public class HibernateApp {

    private static final Logger LOG = LogManager.getLogger(HibernateApp.class);

    public static void main(String args[]) {

        String login = "admin";
        String pass = "admin";

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        if (userDao.validateUser(login)) {

            User user = userDao.getUserByLoginAndPassword(login, pass);

            if (user != null) {

                Role role = user.getRole();

                if (role.getRoleName().equals("admin")) {

                    LOG.info("admin");

                } else {

                    LOG.info("not admin");
                }

                LOG.info("Demo Hibernate Program finished.");

            }
        }
    }
}
