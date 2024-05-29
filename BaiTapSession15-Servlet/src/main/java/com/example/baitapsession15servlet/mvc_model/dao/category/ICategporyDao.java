package com.example.baitapsession15servlet.mvc_model.dao.category;

import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;

import java.util.List;

public interface ICategporyDao {
    List<Category> findAll();
    List<Product> findAllProductByCategory(Integer id);
    Category findById(Integer id);

    void save(Category category);

    void deleteById(Integer id);
}