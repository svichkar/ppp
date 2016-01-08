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

import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Category> getAllCategories() {
		LOG.entry();
		String sql = "SELECT * FROM category;";
		List<Category> categories = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Category category = new Category(result.getString("name"));
				category.setCategoryId(result.getInt("category_id"));
				categories.add(category);
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all categorys", e));
		}
		return LOG.exit(categories);
	}

	@Override
	public Category getCategoryById(int categoryId) {
		LOG.entry(categoryId);
		String sql = "SELECT * FROM category WHERE category_id = ?;";
		Category category = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, categoryId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				category = new Category(result.getString("name"));
				category.setCategoryId(result.getInt("category_id"));
			}
			LOG.trace("the category was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a category by Id", e ));
		}
		return LOG.exit(category);
	}

	@Override
	public void createCategory(Category category) {
		LOG.entry(category);
		String sql = "INSERT INTO category (name) VALUES (?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, category.getName());
			statem.executeUpdate();
			LOG.exit("category was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create a category", e));
		}
	}

	@Override
	public void updateCategory(Category category) {
		LOG.entry(category);
		String sql = "UPDATE category SET name = ? WHERE category_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, category.getName());
			statem.setLong(2, category.getCategoryId());
			statem.executeUpdate();
			LOG.exit("category with id: " + category.getCategoryId() + " was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the category", e));
		}
	}

	@Override
	public void deleteCategory(Category category) {
		LOG.entry(category);
		String sql = "DELETE FROM category WHERE category_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, category.getCategoryId());
			statem.executeUpdate();
			LOG.exit("category with id: " + category.getCategoryId() + " was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the category", e));
		}
	}
}
