<%@ page import="com.foodApp.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FoodApp Sidebar</title>
    <!-- Add your custom stylesheet link here -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/style.css">
</head>
<body>
    <!-- Sidebar Section -->
    <div id="sidebar">
        <% 
            // Fetch the user object from the request
            User user = (User) request.getAttribute("user");
            boolean isAdmin = user != null && user.getIsAdmin(); // Check if the user is an admin
        %>
        <h2>FoodApp</h2>
        <ul class="list-group">
            <li class="list-group-item"><a href="/foodapp/home?id=<%= user.getId() %>">Home</a></li>
            <li class="list-group-item"><a href="/foodapp/menu?id=<%= user.getId() %>">Menu</a></li>
            <li class="list-group-item"><a href="/foodapp/profile?id=<%= user.getId() %>">Profile</a></li>
            
            <% if (!isAdmin) { %>
                <!-- Show Orders and Contact Us only if the user is not an admin -->
                <li class="list-group-item"><a href="/foodapp/orders?id=<%= user.getId() %>">Orders</a></li>
                <li class="list-group-item"><a href="/foodapp/contactus?id=<%= user.getId() %>">Contact Us</a></li>
            <% } %>
            
            <% if (isAdmin) { %>
                <!-- Show Messages only if the user is an admin -->
                <li class="list-group-item"><a href="/foodapp/getmessages?id=<%= user.getId() %>">Messages</a></li>
            <% } %>
        </ul>
    </div>
</body>
</html>
