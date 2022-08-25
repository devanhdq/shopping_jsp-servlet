package com.shopping.servlet;

import com.shopping.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset:UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Cart> list = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));

            Cart cart = new Cart();
            cart.setId(id);
            cart.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            if (cart_list == null) {
                list.add(cart);
                session.setAttribute("cart-list", list);
                response.sendRedirect("index.jsp");
            } else {
                list = cart_list;
                boolean exits = false;

                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        exits = true;
                        out.println("<h3 style='text-align: center;'>Item already exits in Cart. <a href='cart.jsp' class='btn btn-danger'>Go to Cart Page</a></h3>");
                    }
                }
                if (!exits) {
                    list.add(cart);
                    response.sendRedirect("index.jsp");
                }
            }
        }
    }
}
