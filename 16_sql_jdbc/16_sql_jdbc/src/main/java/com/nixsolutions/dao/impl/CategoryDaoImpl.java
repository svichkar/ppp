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

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Category> getAllCategories() {
		String sql = "SELECT * FROM category;";
		List<Category> categories = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Category category = new Category(result.getString("name"));
				category.setCategoryId(result.getInt("category_id"));
				categories.add(category);
				// need to insert assertion - if transaction was done
			}
			LOG.trace("all the categorys were retrieved");
		} catch (SQLException e) {
			LOG.error("not able to get categorys", e);
			LOG.throwing(new DaoException("not able to get all categorys"));
		}
		return categories;
	}

	@Override
	public Category getCategoryById(int categoryId) {
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
			LOG.error("not able to get a category by Id", e);
			LOG.throwing(new DaoException("not able to get a category by Id"));
		}
		return category;
	}

	@Override
	public void createCategory(Category category) {
		String sql = "INSERT INTO category (name) VALUES (?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, category.getName());
			statem.executeUpdate();
			LOG.trace("category was created");
			// need to insert assertion - if transaction was done
		} catch (SQLException e) {
			LOG.error("not able to create a category", e);
			LOG.throwing(new DaoException("not able to create a category"));
		}
	}

	@Override
	public void updateCategory(Category category) {
		String sql = "UPDATE category SET name = ? WHERE category_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, category.getName());
			statem.setLong(2, category.getCategoryId());
			statem.executeUpdate();
			LOG.trace("category with id: " + category.getCategoryId() + " was updated");
			// need to insert assertion - if transaction was done
		} catch (SQLException e) {
			LOG.error("not able to update the category", e);
			LOG.throwing(new DaoException("not able to update the category"));
		}
	}

	@Override
	public void deleteCategory(Category category) {
		String sql = "DELETE FROM category WHERE category_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, category.getCategoryId());
			statem.executeUpdate();
			// need to insert assertion - if transaction was done
		} catch (SQLException e) {
			LOG.error("not able to delete the category", e);
			LOG.throwing(new DaoException("not able to delete the category"));
		}

	}

}
