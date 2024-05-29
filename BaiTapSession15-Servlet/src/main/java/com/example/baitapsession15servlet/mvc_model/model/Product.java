package com.example.baitapsession15servlet.mvc_model.model;

import java.util.Date;

public class Product {

    private Integer product_id;
    private Category category;
    private String product_name;
    private Double product_price;
    private Integer totalPages;
    private Date yearCreate;
    private String product_author;
    private boolean product_status;

    public Product(){
        this.category = new Category();
    }

    public Category getCategory() {
        return this.category;
    }

    public Product(Integer product_id, Category category, String product_name, Double product_price, Integer totalPages, Date yearCreate, String product_author, boolean product_status) {

        this.product_id = product_id;
        this.category = category;
        this.product_name = product_name;
        this.product_price = product_price;
        this.totalPages = totalPages;
        this.yearCreate = yearCreate;
        this.product_author = product_author;
        this.product_status = product_status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Date getYearCreate() {
        return yearCreate;
    }

    public void setYearCreate(Date yearCreate) {
        this.yearCreate = yearCreate;
    }

    public String getProduct_author() {
        return product_author;
    }

    public void setProduct_author(String product_author) {
        this.product_author = product_author;
    }

    public boolean isProduct_status() {
        return product_status;
    }

    public void setProduct_status(boolean product_status) {
        this.product_status = product_status;
    }
    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", category=" + category +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", totalPages=" + totalPages +
                ", yearCreate=" + yearCreate +
                ", product_author='" + product_author + '\'' +
                ", product_status=" + product_status +
                '}';
    }
}


