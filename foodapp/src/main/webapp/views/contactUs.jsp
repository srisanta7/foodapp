<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us - FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/contactUs.css">
</head>
<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <%@ include file="sidebar.jsp" %>
    </div>

    <!-- Main Content Section -->
    <div class="content">
        <h2>Contact Us</h2>
        <p>If you have any questions, feel free to reach out to us using the form below:</p>
        
        <form action="${pageContext.request.contextPath}/foodapp/message?id=<%= user.getId() %>" method="post" class="contact-form">
            <div class="form-group">
                <label for="name">Your Name</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Enter your name" required>
            </div>
            
            <div class="form-group">
                <label for="email">Your Email</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="Enter your email" required>
            </div>
            
            <div class="form-group">
                <label for="message">Your Message</label>
                <textarea id="message" name="message" class="form-control" rows="5" placeholder="Write your message here" required></textarea>
            </div>
            
            <button type="submit" class="btn btn-primary">Send Message</button>
        </form>
    </div>

</body>
</html>
