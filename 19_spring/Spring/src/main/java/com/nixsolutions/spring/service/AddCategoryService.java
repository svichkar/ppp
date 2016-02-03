package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.CategoryDAO;
import com.nixsolutions.spring.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kozlovskij on 2/3/2016.
 */
@Service
public class AddCategoryService {
    @Autowired
    CategoryDAO categoryDAO;
    public List<Category> categories () {
        return categoryDAO.findAll();
    }
    public Long create (String categoryName){
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryDAO.create(category);
        return category.getCategoryId();
    }
}
