<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodApp.model.UserMessage" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Messages - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/showMessages.css">
</head>
<body>
    <!-- Sidebar Section -->
    <div class="sidebar">
        <%@ include file="sidebar.jsp" %>
    </div>

     <!-- Main Content Section -->
    <div class="main-content">
        <h1>User Messages</h1>
       
        <div class="message-cards">
            <%
                // Retrieve the list of user messages from the request
                List<UserMessage> messages = (List<UserMessage>) request.getAttribute("userMessage");

                if (messages != null && !messages.isEmpty()) {
                    for (UserMessage message : messages) {
            %>
            <!-- Message Card -->
            <div class="message-card">
                <div class="message-header">
                    <h2><%= message.getName() != null ? message.getName() : "Anonymous" %></h2>
                    <p class="message-email"><%= message.getEmail() != null ? message.getEmail() : "No email provided" %></p>
                </div>
                <div class="message-body">
                    <p><%= message.getMessage() != null ? message.getMessage() : "No message content" %></p>
                </div>
                <!-- Delete Button -->
                <a 
                    href="/foodapp/deleteMessage?userId=<%= user.getId() %>&messageId=<%= message.getId() %>" 
                    class="delete-button" 
                    onclick="return confirm('Are you sure you want to delete this message?');">
                    Delete
                </a>
            </div>
            <% 
                    }
                } else { 
            %>
            <p class="no-messages">No messages to display!</p>
            <% } %>
        </div>
    </div>
</body>
</html>
