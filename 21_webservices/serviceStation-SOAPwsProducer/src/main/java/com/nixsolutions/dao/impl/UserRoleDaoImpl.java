/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.List;

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
	public UserRole getUserRole(Integer userRoleId) {
		UserRole role = new UserRole();
		try {
			role = (UserRole) sessionFactory.getCurrentSession().createCriteria(UserRole.class)
					.add(Restrictions.eq("userRoleId", userRoleId)).uniqueResult();
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
	public UserRole getUserRole(String userRoleName) {
		UserRole role = new UserRole();
		try {
			role = (UserRole) sessionFactory.getCurrentSession().createCriteria(UserRole.class)
					.add(Restrictions.eq("userRoleName", userRoleName)).uniqueResult();
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
	public void createNewUserRole(UserRole userRole) {
		try {
			sessionFactory.getCurrentSession().save(userRole);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getAllRoles() {
		List<UserRole> roleList = null;
		try {
			roleList = sessionFactory.getCurrentSession().createCriteria(UserRole.class).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return roleList;
	}

}
