<%@page import="com.foodApp.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Menu - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/editmenu.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar Section -->
        <div class="sidebar">
            <%@ include file="sidebar.jsp" %>
        </div>

        <!-- Main Content Section -->
        <main class="content">
            <h1>Edit Menu</h1>

            <!-- Card Section for items -->
            <div class="card-container">
                <% 
                    // Fetching the products list from the request
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                %>
                    <div class="item-card">
                        <h2><%= product.getName() %></h2>
                        <p><%= product.getDescription() %></p>
                        <p><strong>Price:</strong> $<%= product.getPrice() %></p>

                        <!-- Edit and Delete Buttons -->
                        <div class="actions">
                            <button onclick="location.href='/product/edititem?pid=<%= product.getId()%>&uid=<%= user.getId()%>'">Edit</button>
                            <button onclick="location.href='/product/deleteitem?pid=<%= product.getId()%>&uid=<%= user.getId()%>'">Delete</button>
                        </div>
                    </div>
                <%
                        }
                    } else {
                %>
                    <p>No products available for editing at the moment.</p>
                <%
                    }
                %>
            </div>
        </main>
    </div>
    <script>
    // JavaScript function to confirm delete action
        function confirmDelete(productId) {
            if (confirm('Are you sure you want to delete this item?')) {
                window.location.href = '/product/deleteitem?id=' + productId;
            }
        }
    </script>
</body>
</html>