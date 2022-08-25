<%@ page import="com.shopping.model.User" %>
<%@ page import="com.shopping.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping cart | Login</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form action="user-login" method="post">
                <div class="form-group">
                    <label>Email:</label>
                    <input
                            class="form-control"
                            type="email"
                            name="login-email"
                            required
                            placeholder="Enter your email..."
                    />
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input
                            class="form-control"
                            type="password"
                            name="login-password"
                            required
                            placeholder="Enter your password..."
                    />
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>

    </div>
</div>

<%@include file="includes/foot.jsp" %>
</body>
</html>
