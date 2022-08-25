package com.shopping.servlet;

import com.shopping.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/quantity-inc-dec")
public class QuantityBtnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset:UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> carts = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (action != null && id >= 1) {
                switch (action) {
                    case "inc":
                        for (Cart cart : carts) {
                            if (cart.getId() == id) {
                                int quantity = cart.getQuantity();
                                quantity++;
                                cart.setQuantity(quantity);
                                response.sendRedirect("cart.jsp");
                            }
                        }
                        break;
                    case "dec":
                        for (Cart cart : carts) {
                            if (cart.getId() == id) {
                                int quantity = cart.getQuantity();
                                if (quantity >= 1) {
                                    quantity--;
                                } else {
                                    quantity = 0;
                                }
                                cart.setQuantity(quantity);
                                response.sendRedirect("cart.jsp");
                            }
                        }
                        break;
                    default:
                        break;
                }
            } else {
                response.sendRedirect("cart.jsp");
            }
        }
    }
}
