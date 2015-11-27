package dao;

import java.util.List;

import entity.Category;

public interface CategoryDao {

	public boolean addNewRow(List<String> columns, List<String> elements);
	public boolean update(List<String> columns, List<String> elements,
			String whereState);
	public boolean delete(String whereState);
	public List find(String[] searchField, String searchQuery);
	public Integer getCategoryIdByName(String name);
	public List<Category> getAllCategories();
	public boolean addCategory(Category category);
	public  List<Category> searchCategoryByName(String searchQuery);
	public boolean updateCategory(Category cat);

}
