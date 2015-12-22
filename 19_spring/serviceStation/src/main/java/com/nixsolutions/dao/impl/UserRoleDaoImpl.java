/**
 * 
 */
package com.nixsolutions.dao.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.entity.UserRole;

/**
 * @author ������
 *
 */
@Repository("userRoleDao")
@Transactional
public class UserRoleDaoImpl implements UserRoleDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UserRoleDao#getUserRole(java.
	 * lang.Integer)
	 */
	@Override
	public UserRole getUserRole(Integer user_role_id) {
		UserRole role = new UserRole();
		try {
			role = (UserRole) sessionFactory.getCurrentSession().createCriteria(UserRole.class)
					.add(Restrictions.eq("userRoleId", user_role_id)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UserRoleDao#getUserRole(java.
	 * lang.Integer)
	 */
	@Override
	public UserRole getUserRole(String user_role_name) {
		UserRole role = new UserRole();
		try {
			role = (UserRole) sessionFactory.getCurrentSession().createCriteria(UserRole.class)
					.add(Restrictions.eq("userRoleName", user_role_name)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return role;
	}

	/*
	 * create new sqllab.role for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	public void createNewUserRole(UserRole user_role) {
		try {
			sessionFactory.getCurrentSession().save(user_role);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

}
