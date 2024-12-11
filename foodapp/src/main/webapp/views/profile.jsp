<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/profile.css">
</head>
<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <%@ include file="sidebar.jsp" %>
    </div>

    <!-- Profile Container -->
    <div class="profile-container">
        <div class="profile-card">
            <!-- Profile Picture -->
            <div class="profile-picture">
                <img src="/images/profile.jpg" alt="Profile Picture" class="profile-img">
            </div>

            <!-- Profile Information -->
            <div class="profile-info">
                <h2 class="profile-name"><%= user.getName() != null ? user.getName() : "Guest" %></h2>
                <p class="profile-email"><%= user.getEmail() != null ? user.getEmail() : "Email not provided" %></p>
            </div>

            <!-- Profile Actions -->
            <div class="profile-actions">
                <button onclick="location.href='/foodapp/logout?uid=<%= user.getId() %>'" class="btn btn-logout">Logout</button>
            </div>
        </div>
    </div>

</body>
</html>
