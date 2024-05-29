package com.example.baitapsession15servlet.mvc_model.dao.product;

import com.example.baitapsession15servlet.mvc_model.model.Category;
import com.example.baitapsession15servlet.mvc_model.model.Product;
import com.example.baitapsession15servlet.mvc_model.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    @Override
    public List<Product> findAllProduct() {
        List<Product> products = new ArrayList<>();
        Connection conn = ConnectDB.connection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), null, false);
                Product product = new Product(
                        rs.getInt("id"),
                        category,
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("totalPages"),
                        rs.getDate("yearCreated"),
                        rs.getString("author"),
                        rs.getBoolean("status")
                );
                products.add(product);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return products;
    }

    @Override
    public Product findByIdProduct(Integer id) {
        Product product = null;
        Connection conn = ConnectDB.connection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from product where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), null, false);
                product = new Product(

                        rs.getInt("id"),
                        category,
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("totalPages"),
                        rs.getDate("yearCreated"),
                        rs.getString("author"),
                        rs.getBoolean("status")
                );
            }
            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }




    @Override
    public void saveProduct(Product product) {
        Connection conn = ConnectDB.connection();
        PreparedStatement ps = null;
        try {
            if (product.getProduct_id() == null) {
                ps = conn.prepareStatement("insert into product (category_id, name, price, totalPages, yearCreated, author, status) values (?,?,?,?,?,?,?)");
                ps.setInt(1, product.getCategory().getCategory_id());
                ps.setString(2, product.getProduct_name());
                ps.setDouble(3, product.getProduct_price());
                ps.setInt(4, product.getTotalPages());
                ps.setDate(5, new java.sql.Date(product.getYearCreate().getTime()));
                ps.setString(6, product.getProduct_author());
                ps.setBoolean(7, product.isProduct_status());
                System.out.println(ps);

            } else {
                ps = conn.prepareStatement("update product set category_id = ?, name = ?, price = ?, totalPages = ?, yearCreated = ?, author = ?, status = ? where id = ?");
                ps.setInt(1, product.getCategory().getCategory_id());
                ps.setString(2, product.getProduct_name());
                ps.setDouble(3, product.getProduct_price());
                ps.setInt(4, product.getTotalPages());
                ps.setDate(5, new java.sql.Date(product.getYearCreate().getTime()));
                ps.setString(6, product.getProduct_author());
                ps.setBoolean(7, product.isProduct_status());
                ps.setInt(8, product.getProduct_id());
                System.out.println(ps);
            }
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteByIdProduct(Integer id) {
        Connection conn = ConnectDB.connection();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from product where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
