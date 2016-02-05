package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.dao.StatusDAO;

@Repository("statusDao")
@Transactional
public class StatusDAOImpl implements StatusDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Status findStatusById(Long statusId) {
		Session session = sessionFactory.getCurrentSession();
		Status status = null;
		try {
			status = (Status) session.get(Status.class, statusId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> findAllStatuses() {
		Session session = sessionFactory.getCurrentSession();
		List<Status> statuses = new ArrayList<Status>();
		try {
			statuses = session.createCriteria(Status.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return statuses;
	}
}
