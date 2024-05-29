package com.example.baitapsession15servlet.mvc_model.service.category;

import com.example.baitapsession15servlet.mvc_model.dao.category.CategoryDaoImpl;
import com.example.baitapsession15servlet.mvc_model.dao.category.ICategporyDao;
import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;

import java.awt.*;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {

    private static final ICategporyDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
    @Override
   public List<Product> findAllProductByCategory(Integer id) {
        return categoryDao.findAllProductByCategory(id);
    }
    @Override
    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDao.deleteById(id);
    }

}
