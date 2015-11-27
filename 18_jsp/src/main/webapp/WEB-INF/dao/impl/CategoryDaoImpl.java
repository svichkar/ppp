package dao.impl;

import java.util.Arrays;
import java.util.List;

import dao.CategoryDao;
import entity.Category;

public class CategoryDaoImpl extends AbstractDaoImpl implements CategoryDao {

	public Integer getCategoryIdByName(String name) {
		List<Category> temp = super.find(new String[]{"id"},
				"name='" + name + "'");
		Integer tempInt = temp.get(0).getId();

		return tempInt;

	}

	@Override
	public List<Category> getAllCategories() {
		
		return super.find(null, null);
	}

	@Override
	public boolean addCategory(Category category) {
		
		return super.addNewRow(Arrays.asList("name"), Arrays.asList(category.getName()));
	}

	@Override
	public List<Category> searchCategoryByName(String searchQuery) {
		
		return super.find(null,
				"name like '%" + searchQuery + "%'");
	}

	@Override
	public boolean updateCategory(Category cat) {
	
		return super.update(Arrays.asList("name"), Arrays.asList(cat.getName()), "id="+cat.getId());
	}
}
