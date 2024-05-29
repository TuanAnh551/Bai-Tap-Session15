package com.example.baitapsession15servlet.mvc_model.controller;

import com.example.baitapsession15servlet.mvc_model.dao.category.CategoryDaoImpl;
import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;
import com.example.baitapsession15servlet.mvc_model.service.category.CategoryServiceImpl;
import com.example.baitapsession15servlet.mvc_model.service.product.IProductService;
import com.example.baitapsession15servlet.mvc_model.service.product.ProductImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends HttpServlet {
    public static final IProductService productService = new ProductImpl();
    public static final CategoryServiceImpl categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productAction = request.getParameter("productAction");
        if (productAction != null) {
            switch (productAction) {
                case "LIST":
                    request.setAttribute("products", productService.findAllProduct());
                    request.getRequestDispatcher("/views/product/list-product.jsp").forward(request, response);
                    break;
                case "DELETE":
                    Integer deleteId = Integer.valueOf(request.getParameter("id"));
                    productService.deleteByIdProduct(deleteId);
                    response.sendRedirect("/product?productAction=LIST");
                    break;
                case "ADD":
                    List<Category> categories = categoryService.findAll();
                    request.setAttribute("categories", categories);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product/add-product.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "EDIT":
                    List<Category> categoriesEdit = categoryService.findAll();
                    request.setAttribute("categoriesEdit", categoriesEdit);
                    Integer editId = Integer.valueOf(request.getParameter("id"));
                    Product editProduct = productService.findByIdProduct(editId);
                    request.setAttribute("product",editProduct);
                    System.out.println(editProduct.toString());
                    request.getRequestDispatcher("/views/product/edit-product.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productAction = request.getParameter("productAction");
        if (productAction != null) {
            switch (productAction) {
                case "Add":
                    productService.saveProduct(getProductFromRequest(request));
                    response.sendRedirect("/product?productAction=LIST");
                    break;
                case "EDIT":
                    Integer editId = Integer.valueOf(request.getParameter("id"));
                    Product editProduct = getProductFromRequest(request);
                    editProduct.setProduct_id(editId);
                    productService.saveProduct(editProduct);
                    response.sendRedirect("/product?productAction=LIST");
                    break;
            }
        }
    }

    private Product getProductFromRequest(HttpServletRequest request) {

        Integer categoryId = Integer.valueOf(request.getParameter("category_id"));
        Category category = categoryService.findById(categoryId); // get the category from the id
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        Date created = new Date();
        Integer totalPages = Integer.valueOf(request.getParameter("totalPages"));
        String author = request.getParameter("author");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        System.out.println("name: " + name + " category: " + category + " price: " + price + " totalPages: " + totalPages + " author: " + author + " status: " + status);

        // Null is placed at the beginning as the product id
        return new Product(null, category, name, price, totalPages, created, author, status);
    }
}