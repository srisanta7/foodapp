<%@ page import="com.foodApp.model.OrderItem" %>
<%@ page import="com.foodApp.model.Order" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/orders.css">
</head>
<body>
    <!-- Sidebar Section -->
    <div class="sidebar">
        <%@ include file="sidebar.jsp" %>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <h1>Order History</h1>
        <%
            // Retrieve the list of orders from the request
            List<Order> orderList = (List<Order>) request.getAttribute("orderList");

            // Define a DateTimeFormatter for date and time
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            if (orderList != null && !orderList.isEmpty()) {
                // Sort the orders by date (most recent first)
                orderList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        %>
        <div class="order-cards">
            <% for (Order order : orderList) { %>
                <div class="order-card">
                    <!-- Order Header -->
                    <div class="order-header">
                        <h2>Order ID: <%= order.getId() %></h2>
                        <p>Date: <%= order.getDate().format(dateFormatter) %></p>
                        <p>Time: <%= order.getDate().format(timeFormatter) %></p>
                    </div>

                    <!-- Order Items -->
                    <div class="order-items">
                        <h3>Items:</h3>
                        <ul>
                            <% for (OrderItem item : order.getOrderItems()) { %>
                                <li class="item">
                                    <div class="item-details">
                                        <span class="item-name"><%= item.getProductName() %></span>
                                        <span class="item-quantity">Qty: <%= item.getQuantity() %></span>
                                    </div>
                                    <div class="item-price">$<%= item.getPrice() * item.getQuantity() %></div>
                                </li>
                            <% } %>
                        </ul>
                    </div>

                    <!-- Order Totals -->
                    <div class="order-summary">
                        <p><strong>Subtotal:</strong> $<%= order.getSubtotal() %></p>
                        <p><strong>Tax:</strong> $<%= order.getTax() %></p>
                        <p class="order-total"><strong>Total Price:</strong> $<%= order.getTotal() %></p>
                    </div>
                </div>
            <% } %>
        </div>
        <%
            } else { 
        %>
        <p>No orders found!</p>
        <%
            } 
        %>
    </div>
</body>
</html>
