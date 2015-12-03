/**
 * 
 */
package com.nixsolutions.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.util.HibernateUtil;


/**
 * @author mixeyes
 *
 */
public class TestUser {

	private final static Logger logger = LogManager.getLogger();
	private static Session session = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			UserRole userRole = new UserRole();
			userRole.setUser_role_name("manager");
			User user = new User();
			user.setUser_login("admin");
			user.setUser_password("admin");
			user.setUserRole(userRole);
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			User user1= (User) session.get(User.class, "admin");
			session.getTransaction().commit();
			logger.debug(user1.getUser_login());
			
		} catch (Exception e) {
			logger.catching(e);
		}
	}

}
