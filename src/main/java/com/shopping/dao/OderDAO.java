package com.shopping.dao;

import com.shopping.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OderDAO {
    private Connection connection;
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public OderDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertOrder(Order order) {
        boolean result = false;
        try {
            sql = "INSERT INTO orders(p_id, u_id, o_quantity, o_date) VALUES(?,?,?,?)";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, order.getDate());

            preparedStatement.executeQuery();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }
}
