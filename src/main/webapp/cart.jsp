<%@ page import="com.shopping.model.User" %>
<%@ page
        import="java.util.ArrayList" %>
<%@ page import="com.shopping.model.Cart" %>
<%@
        page import="java.util.List" %>
<%@ page import="com.shopping.dao.ProductDAO" %>
<%@ page import="com.shopping.connection.DBConnection" %>
<%@ page
        contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% User auth =
        (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
    ArrayList<Cart>
            cart_list = (ArrayList<Cart
            >) session.getAttribute("cart-list");
    List<Cart>
            carts = null;
    double totalPrice;
    if (cart_list != null) {
        ProductDAO
                productDAO = new ProductDAO(DBConnection.getConnection());
        carts =
                productDAO.getCardProducts(cart_list);
        totalPrice =
                productDAO.getTotalPrice(cart_list);
        request.setAttribute("cart_list",
                cart_list);
        request.setAttribute("totalPrice", totalPrice);
    } %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <%@include file="includes/head.jsp" %>
</head>
<style>
    .table tbody td {
        vertical-align: middle;
    }

    .btn-increment,
    .btn-decrement {
        box-shadow: none;
        font-size: 25px;
    }
</style>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
    <div class="d-flex py-3">
        <h3>Total Price: ${(totalPrice>0)? totalPrice:0} USD</h3>
        <a href="" class="btn btn-primary mx-5">Checkout</a>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <% if (cart_list != null) {
            for (Cart cart : carts) { %>
        <tr>
            <td><%=cart.getName()%>
            </td>
            <td><%=cart.getCategory()%>
            </td>
            <td><%=cart.getPrice()%>
            </td>
            <td>
                <form action="order-now" method="post" class="form-inline"></form>
                <input type="hidden"
                       name="id"
                       value="<%=cart.getId()%>"
                       class="form-input"
                />
                <div class="form-group d-flex justify-content-between w-50">
                    <a href="quantity-inc-dec?action=dec&id=<%=cart.getId()%>"
                       class="btn btn-sm btn-increment">
                        <i class="fas fa-minus-square"></i>
                    </a>
                    <input type="text"
                           name="quantity"
                           class="form-control w-50"
                           value="<%=cart.getQuantity()%>"
                           readonly
                    />
                    <a href="quantity-inc-dec?action=inc&id=<%=cart.getId()%>"
                       class="btn btn-sm btn-decrement">
                        <i class="fas fa-plus-square"></i>
                    </a>
                </div>
            </td>
            <td>
                <button type="submit" class="btn btn-sm btn-success">Buy Now</button>
            </td>
            <td>
                <a href="remove-from-cart?id=<%=cart.getId()%>"
                   class="btn btn-sm btn-danger">
                    Remove
                </a>
            </td>
        </tr>
        <% }
        } %>
        </tbody>
    </table>
</div>

<%@include file="includes/foot.jsp" %>
</body>
</html>
</Cart></Cart
></Cart
>
