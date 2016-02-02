package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.entity.Category;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getAllCategories() {
		LOG.entry();
		List<Category> categories = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Category.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		categories = criteria.list();
		return LOG.exit(categories);
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		LOG.entry(categoryId);
		Category category = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Category.class, "category")
				.add(Restrictions.eq("category.categoryId", categoryId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return LOG.exit(category);
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		LOG.entry(categoryName);
		Category category = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Category.class, "category")
				.add(Restrictions.eq("category.name", categoryName))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		category = (Category) criteria.uniqueResult();
		return LOG.exit(category);
	}

	@Override
	public void createCategory(Category category) {
		LOG.entry(category);
		Session session = sessionFactory.getCurrentSession();
		session.save(category);
	}

	@Override
	public void updateCategory(Category category) {
		LOG.entry(category);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}

	@Override
	public void deleteCategory(Category category) {
		LOG.entry(category);
		Session session = sessionFactory.getCurrentSession();
		session.delete(category);
	}
}
