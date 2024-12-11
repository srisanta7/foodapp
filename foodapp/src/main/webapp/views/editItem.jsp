<%@ page import="com.foodApp.model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/editItem.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar Section -->
        <div class="sidebar">
            <%@ include file="sidebar.jsp" %>
        </div>

        <!-- Main Content Section -->
        <main class="content">
            <h1>Edit Item</h1>

            <% 
                // Fetch the product object from the request
                Product product = (Product) request.getAttribute("product");
                if (product != null) {
            %>
            <form action="/product/updateitemindb?uid=<%= user.getId()%>" method="post">
                <input type="hidden" name="id" value="<%= product.getId() %>">

                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="<%= product.getName() %>" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" required><%= product.getDescription() %></textarea>
                </div>

                <div class="form-group">
                    <label for="price">Price ($):</label>
                    <input type="number" id="price" name="price" step="0.01" value="<%= product.getPrice() %>" required>
                </div>

                <button type="submit" class="save-button">Save Changes</button>
                <button type="button" class="cancel-button" onclick="window.location.href='/foodapp/editmenu?uid=<%= user.getId()%>'">Cancel</button>
            </form>
            <% 
                } else { 
            %>
                <p>Product not found. Please go back to the menu and try again.</p>
            <%
                }
            %>
        </main>
    </div>
</body>
</html>
