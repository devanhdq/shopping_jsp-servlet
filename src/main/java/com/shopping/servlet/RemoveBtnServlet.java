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

@WebServlet("/remove-from-cart")
public class RemoveBtnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset:UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            if (id != null) {
                ArrayList<Cart> carts = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                for (Cart cart : carts) {
                    int idParameter = Integer.parseInt(request.getParameter("id"));
                    if (cart.getId() == idParameter) {
                        carts.remove(cart);
                        break;
                    }
                }
                response.sendRedirect("cart.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }

        }

    }
}
