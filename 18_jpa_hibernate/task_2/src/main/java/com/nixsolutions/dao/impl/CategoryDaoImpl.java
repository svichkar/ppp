package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Category> getAllCategories() {
		LOG.entry();
		List<Category> categories = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		categories = criteria.list();
		transaction.commit(); 
		return LOG.exit(categories);
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		LOG.entry(categoryId);
		Category category = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Category.class, "category")
				.add(Restrictions.eq("category.categoryId", categoryId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		category = (Category) criteria.uniqueResult();
		transaction.commit(); 
		return LOG.exit(category);
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		LOG.entry(categoryName);
		Category category = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Category.class, "category")
				.add(Restrictions.eq("category.name", categoryName)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		category = (Category) criteria.uniqueResult();
		transaction.commit(); 
		return LOG.exit(category);
	}

	@Override
	public void createCategory(Category category) {
		LOG.entry(category);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(category);
		transaction.commit(); 
	}

	@Override
	public void updateCategory(Category category) {
		LOG.entry(category);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(category);
		transaction.commit(); 
	}

	@Override
	public void deleteCategory(Category category) {
		LOG.entry(category);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(category);
		transaction.commit(); 
	}
}
