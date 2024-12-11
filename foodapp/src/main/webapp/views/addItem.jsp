<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Item - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/addItems.css">
</head>
<body>
    <!-- Sidebar Section -->
    <div class="sidebar">
        <%@ include file="sidebar.jsp" %>
    </div>

    <!-- Main Content Section -->
    <div class="form-container">
        <h2>Add New Item</h2>
        
        <!-- Form for Adding an Item -->
        <form action="/product/addproducttodb?uid=<%= user.getId()%>" method="post">
            <div class="form-group">
                <label for="itemName">Item Name:</label>
                <input type="text" id="itemName" name="name" placeholder="Enter item name" required>
            </div>
        
            <div class="form-group">
                <label for="itemPrice">Item Price ($):</label>
                <input type="number" id="itemPrice" name="price" step="0.01" placeholder="Enter price in dollars" required>
            </div>

            <div class="form-group">
                <label for="itemDescription">Description:</label>
                <textarea id="itemDescription" name="description" rows="4" placeholder="Enter item description" required></textarea>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="submit-button">Submit</button>
                <button type="button" class="cancel-button" onclick="window.history.back()">Cancel</button>
            </div>
        </form>
    </div>
</body>
</html>
