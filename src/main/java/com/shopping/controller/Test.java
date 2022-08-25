package com.shopping.controller;

import com.shopping.connection.DBConnection;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBConnection.getConnection();
    }
}
