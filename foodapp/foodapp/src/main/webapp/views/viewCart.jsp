<%@page import="java.util.List"%>
<%@page import="com.foodApp.model.Cart"%>
<%@page import="com.foodApp.model.CartItem"%>
<%@page import="com.foodApp.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp - Order Preview</title>
    <link rel="stylesheet" type="text/css" href="/views/viewCart.css">
</head>
<body>
    <!-- Main Container -->
    <div class="container">
        <!-- Sidebar Section -->
        <div class="sidebar">
            <%@ include file="sidebar.jsp" %>
        </div>

        <!-- Main Content Section -->
        <main class="content">
            <h1>Order Preview</h1>

            <!-- Order Items Section -->
            <div class="order-items">
                <h2>Your Order</h2>
                <%
                    Cart cart = (Cart) request.getAttribute("cart");
                    List<CartItem> cartItems = cart != null ? cart.getCartItems() : null;

                    if (cartItems != null && !cartItems.isEmpty()) {
                %>
                <table class="order-table">
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (CartItem item : cartItems) {
                        %>
                        <tr>
                            <td><%= item.getProductName() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>$<%= item.getPrice() %></td>
                            <td>$<%= item.getPrice() * item.getQuantity() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

                <div class="order-summary">
                    <p><strong>Subtotal:</strong> $<%= cart.getSubtotal() %></p>
                    <p><strong>Tax:</strong> $<%= cart.getTax() %></p>
                    <p><strong>Order Total:</strong> $<%= cart.getTotal() %></p>
                </div>

                <form action="/foodapp/createorder?cartId=<%= cart.getCartId() %>" method="post">
                    <input type="hidden" name="cartId" value="<%= cart.getCartId() %>">
                    <div class="actions">
                        <button type="submit" class="btn btn-primary">Place Order</button>
                    </div>
                </form>
                <%
                    } else {
                %>
                <p class="empty-cart">No items in your order.</p>
                <% } %>
            </div>
        </main>
    </div>
</body>
</html>
