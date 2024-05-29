package com.example.baitapsession15servlet.mvc_model.service.product;

import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProduct();

    Product findByIdProduct(Integer id);

    void saveProduct(Product product);

    void deleteByIdProduct(Integer id);
}
