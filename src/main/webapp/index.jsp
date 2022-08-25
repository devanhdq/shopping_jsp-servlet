<%@ page import="com.shopping.connection.DBConnection" %>
<%@ page
        import="com.shopping.model.User" %>
<%@ page import="com.shopping.dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shopping.model.Product" %>
<%@ page import="com.shopping.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8" %>
<% User auth = (User)
        request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
    ProductDAO productDAO = new ProductDAO(DBConnection.getConnection());
    List<Product> products = productDAO.getAllProducts();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping home page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container" style="gap:20px">
    <div class="card-header my-3">
        <div class="row">
            <%
                if (!products.isEmpty()) {
                    for (Product product : products) { %>

            <div class="col-md-3 my-3">
                <div class="card w-100" style="max-width: 18rem">
                    <img
                            class="card-img-top img-fluid"
                            src="<%=product.getImage()%>"
                            alt="Card image cap"
                            style="height:160px;width: 240px; object-fit: cover"
                    />
                    <div class="card-body">
                        <h5 class="card-title"><%=product.getName()%>
                        </h5>
                        <h6 class="price">Price : <%=product.getPrice()%>
                        </h6>
                        <h6 class="category">Category : <%=product.getCategory()%>
                        </h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <a href="add-to-cart?id=<%=product.getId()%>" class="btn btn-dark">Add to Card</a>
                            <a href="#" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div>
            </div>
            <% }
            }%>


        </div>
    </div>
</div>
<%@include file="includes/foot.jsp" %>
</body>
</html>
