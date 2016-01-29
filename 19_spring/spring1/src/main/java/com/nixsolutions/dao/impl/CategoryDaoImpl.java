package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Category;

@Repository("CategoryDao")
public class CategoryDaoImpl implements CategoryDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> getAllCategories() {
		LOG.entry();
		List<Category> categories = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Category.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			categories = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(categories);
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		LOG.entry(categoryId);
		Category category = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Category.class, "category")
					.add(Restrictions.eq("category.categoryId", categoryId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			category = (Category) criteria.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(category);
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		LOG.entry(categoryName);
		Category category = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Category.class, "category")
					.add(Restrictions.eq("category.name", categoryName))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			category = (Category) criteria.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(category);
	}

	@Override
	public void createCategory(Category category) {
		LOG.entry(category);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.save(category);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

	}

	@Override
	public void updateCategory(Category category) {
		LOG.entry(category);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.saveOrUpdate(category);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

	}

	@Override
	public void deleteCategory(Category category) {
		LOG.entry(category);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.delete(category);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

	}
}
