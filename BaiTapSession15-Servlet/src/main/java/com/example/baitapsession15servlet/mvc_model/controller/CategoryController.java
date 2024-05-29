package com.example.baitapsession15servlet.mvc_model.controller;

import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;
import com.example.baitapsession15servlet.mvc_model.service.category.CategoryServiceImpl;
import com.example.baitapsession15servlet.mvc_model.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/Category")
public class CategoryController extends HttpServlet {
    public static final ICategoryService categoryService = new CategoryServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryAction = request.getParameter("categoryAction");
        if (categoryAction != null) {
            switch (categoryAction) {
                case "LIST":
                    List<Category> categories = categoryService.findAll();
                    request.setAttribute("categories", categories);
                    request.getRequestDispatcher("views/category/list-category.jsp").forward(request, response);
                    break;
                case "DELETE":
                    Integer deleteId = Integer.valueOf(request.getParameter("category_id"));
                    categoryService.deleteById(deleteId);
                    response.sendRedirect("/Category?categoryAction=LIST");
                    break;
                case "ADD":
                    request.getRequestDispatcher("/views/category/add-category.jsp").forward(request, response);
                    break;
                case "EDIT":
                    Integer editId = Integer.valueOf(request.getParameter("category_id"));
                    Category editCategory = categoryService.findById(editId);
                    request.setAttribute("category", editCategory);
                    request.getRequestDispatcher("views/category/edit-category.jsp").forward(request, response);
                    break;
                case "SEELISTPRODUCTOFACATEGORY":
                    Integer seeListProductOfCategory = Integer.valueOf(request.getParameter("category_id"));
                    List<Product> products = categoryService.findAllProductByCategory(seeListProductOfCategory);
                    request.setAttribute("products", products);
                    request.getRequestDispatcher("views/category/list-product-of-category.jsp").forward(request, response);
                    break;


            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryAction = request.getParameter("categoryAction");
        if (categoryAction != null) {
            switch (categoryAction) {
                case "ADD":
                    Category category = getCategoryFromRequest(request);
                    categoryService.save(category);
                    response.sendRedirect("/Category?categoryAction=LIST");
                    break;

                case "EDIT":
                    Integer editId = Integer.valueOf(request.getParameter("id"));
                    System.out.println(editId);
                    Category editCategory = getCategoryFromRequest(request);
                    editCategory.setCategory_id(editId);
                    categoryService.save(editCategory);
                    response.sendRedirect("/Category?categoryAction=LIST");
                    break;


            }
        }
    }

    private Category getCategoryFromRequest(HttpServletRequest request) {
        String category_name = request.getParameter("name");
        System.out.println(category_name);
        boolean category_status = Boolean.parseBoolean(request.getParameter("status"));
        System.out.println(category_status);
        return new Category(null, category_name, category_status);

    }
}