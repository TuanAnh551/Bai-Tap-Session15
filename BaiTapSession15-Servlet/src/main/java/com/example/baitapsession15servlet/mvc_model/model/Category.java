package com.example.baitapsession15servlet.mvc_model.model;

import java.util.Collection;

public class Category {
    private Integer category_id;
    private String category_name;
    private boolean category_status;
    private Collection<Product> products;

    public Category() {
    }

    public Category(Integer category_id, String category_name, boolean category_status) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_status = category_status;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public boolean isCategory_status() {
        return category_status;
    }

    public void setCategory_status(boolean category_status) {
        this.category_status = category_status;
    }

    public Collection<Product> getProducts() {

        return this.products;
    }
}
