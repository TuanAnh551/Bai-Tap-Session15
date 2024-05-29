package com.example.baitapsession15servlet.mvc_model.dao.category;

import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;
import com.example.baitapsession15servlet.mvc_model.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategporyDao {
    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        Connection CategoryConnection = ConnectDB.connection();
        try {
            CallableStatement callableStatement = CategoryConnection.prepareCall("select * from category");
            System.out.println(callableStatement);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("status")
                );
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(CategoryConnection);
        }
    }
    @Override
    public List<Product> findAllProductByCategory(Integer id) {
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectDB.connection();
        try {
            CallableStatement callableStatement = connection.prepareCall("select * from product where category_id = ?");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("category_id"), null, false);
                Product product = new Product(
                        resultSet.getInt("id"),
                        category,
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("totalPages"),
                        resultSet.getDate("yearCreated"),
                        resultSet.getString("author"),
                        resultSet.getBoolean("status")
                );
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }

    @Override
    public Category findById(Integer id) {
        Category category = null;
        Connection connection = ConnectDB.connection();
        try {
            CallableStatement callableStatement = connection.prepareCall("select * from category where category_id = ?");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("status")
                );
            }
            return category;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }

    @Override
    public void save(Category category) {
        Connection connection = ConnectDB.connection();
        CallableStatement callableStatement = null;
        try{
            if (category.getCategory_id() == null){
               callableStatement = connection.prepareCall("insert into category(name, status) values (?, ?)");
                callableStatement.setString(1, category.getCategory_name());
                callableStatement.setBoolean(2, category.isCategory_status());
                System.out.println(callableStatement);
            } else {
                callableStatement = connection.prepareCall("update category set name = ?, status = ? where category_id = ?");
                callableStatement.setString(1, category.getCategory_name());
                callableStatement.setBoolean(2, category.isCategory_status());
                callableStatement.setInt(3, category.getCategory_id());
                System.out.println(callableStatement);
            }
            callableStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = ConnectDB.connection();
        try{
         CallableStatement callableStatement = connection.prepareCall("delete from category where category_id = ?");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }

    }

}
