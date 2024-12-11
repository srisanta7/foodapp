<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Success - FoodApp</title>
<link rel="stylesheet" type="text/css" href="/views/ordersuccess.css">
<style>
        /* General Styles */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            height: 100vh; /* Full viewport height */
            display: flex; /* Enable flexbox */
           
        }
        
        /* Success Message Section */
        .success-message {
            text-align: center;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 10px;
            padding: 20px 40px;
            font-size: 1.5em;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            white-space: nowrap; /* Prevent line wrapping */
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .success-message {
                width: 90%; /* Adjust width on smaller screens */
            }
        }
    </style>
</head>
<body>
	<!-- Sidebar Section -->
	<div class="sidebar">
		<%@ include file="sidebar.jsp"%>
	</div>

	<!-- Main Content Section -->
	<div class="content">
		<div class="success-message">🎉 Order Placed Successfully! 🎉</div>
	</div>
</body>
</html>
