package com.shopping.servlet;

import com.shopping.connection.DBConnection;
import com.shopping.dao.OderDAO;
import com.shopping.model.Order;
import com.shopping.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset:UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            User userAuth = (User) request.getSession().getAttribute("auth");
            if (userAuth != null) {
                String productId = request.getParameter("id");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                if (quantity <= 0) {
                    quantity = 1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUserId(userAuth.getId());
                orderModel.setQuantity(quantity);
                orderModel.setDate(formatter.format(date));

                OderDAO oderDAO = new OderDAO(DBConnection.getConnection());
                boolean result = oderDAO.insertOrder(orderModel);
                if (result) {
                    response.sendRedirect("order.jsp");
                } else {
                    out.println("Order fail");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
