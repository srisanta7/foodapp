<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    body {
        background-color: #f0f2f5;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .registration-form {
        background-color: #ffffff;
        padding: 2rem;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        width: 100%;
        max-width: 400px;
    }
    .registration-form h1 {
        text-align: center;
        color: #333;
        margin-bottom: 1.5rem;
    }
    .form-control {
        border-radius: 20px;
    }
    .form-control:focus {
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        border-color: #80bdff;
    }
    .btn-primary {
        width: 100%;
        border-radius: 20px;
        background: linear-gradient(45deg, #4a90e2, #007bff);
        border: none;
    }
    .btn-primary:hover {
        background: linear-gradient(45deg, #007bff, #4a90e2);
    }
</style>
</head>
<body>
<div class="registration-form">
    <h1>Login</h1>
    
    <!-- Spring form tag with modelAttribute="user" to bind the form to User model -->
    <form:form action="${pageContext.request.contextPath}/admin/validate" method="post" modelAttribute="user">
        
        <div class="form-group">
            <label for="Email1" class="form-label">Email Address</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                </div>
                <form:input path="email" id="Email1" type="email" class="form-control" required="true" placeholder="Enter your email" />
            </div>
        </div>
        
        <div class="form-group">
            <label for="Password1" class="form-label">Password</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                </div>
                <form:password path="password" id="Password1" class="form-control" required="true" placeholder="Enter your password" />
            </div>
        </div>
        
        <button type="submit" class="btn btn-primary mt-3">Login</button>
    </form:form>
    <div>
    <p>don't have an account?<a href="/admin/adminRegistration">SignUp</a></p>
    
    </div>
</div>
<!-- Font Awesome for icons -->
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
