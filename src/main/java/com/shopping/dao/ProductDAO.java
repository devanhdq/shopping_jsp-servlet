package com.shopping.dao;

import com.shopping.model.Cart;
import com.shopping.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }


    public List<Product> getAllProducts() throws SQLException {
        List<Product> result = new ArrayList<Product>();
        try {
            sql = "SELECT * FROM products";
            preparedStatement = this.connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product row = new Product();
                row.setId(resultSet.getInt("id"));
                row.setName(resultSet.getString("name"));
                row.setCategory(resultSet.getString("category"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<Cart> getCardProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<Cart>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    sql = "SELECT * FROM products where id=?";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Cart row = new Cart();
                        row.setId(resultSet.getInt("id"));
                        row.setName(resultSet.getString("name"));
                        row.setCategory(resultSet.getString("category"));
                        row.setPrice(resultSet.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return products;
    }

    public double getTotalPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    sql = "SELECT price FROM products WHERE id=?";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        sum += resultSet.getDouble("price") * item.getQuantity();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }
}
