<%@page import="java.util.List"%>
<%@page import="com.foodApp.model.Product"%>
<%@page import="com.foodApp.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodApp Menu</title>
<link rel="stylesheet" type="text/css" href="/views/menu.css">
<script>
        // JavaScript functions for handling quantity changes
        function increaseQuantity(productId) {
            const input = document.getElementById('quantityInput-' + productId);
            input.value = parseInt(input.value) + 1;
            document.getElementById('quantity-' + productId).value = input.value;
        }

        function decreaseQuantity(productId) {
            const input = document.getElementById('quantityInput-' + productId);
            if (parseInt(input.value) > 0) {
                input.value = parseInt(input.value) - 1;
                document.getElementById('quantity-' + productId).value = input.value;
            }
        }

        function updateQuantity(productId) {
            const input = document.getElementById('quantityInput-' + productId);
            document.getElementById('quantity-' + productId).value = input.value;
        }
    </script>
</head>
<body>
	<!-- Main Container -->
	<div class="container">
		<!-- Sidebar Section -->
		<div class="sidebar">
			<%@ include file="sidebar.jsp"%>
		</div>

		<!-- Main Content Section -->
		<main class="content">
			<h1>Our Menu</h1>

			<!-- Admin Action Buttons (Visible only to Admins) -->

			<% if (user != null && user.getIsAdmin()) { %>
			<div class="admin-actions">
				<button class="b1"
					onclick="location.href='/foodapp/additem?uid=<%= user.getId() %>'">Add
					Item</button>
				<button class="b2"
					onclick="location.href='/foodapp/editmenu?uid=<%= user.getId() %>'">Edit
					Menu</button>
			</div>
			<% } %>

			<!-- Form to submit the cart items -->
			<form action="${pageContext.request.contextPath}/foodapp/submitcart"
				method="post">
				<input type="hidden" name="userId"
					value="<%= user != null ? user.getId() : "" %>">

				<div class="card-container">
					<!-- Iterate over the products list received from the controller -->
					<% 
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
        %>
					<div class="item-card">
						<h2><%= product.getName() %></h2>
						<p><%= product.getDescription() %></p>
						<p>
							<strong>Price:</strong> $<%= product.getPrice() %></p>

						<!-- Quantity Controls -->
						<% if (user == null || !user.getIsAdmin()) { %>
						<!-- Show only for non-admin users -->
						<div class="quantity-controls">
							<button class="quantity-btn" type="button"
								onclick="decreaseQuantity(<%= product.getId() %>)">-</button>
							<input class="quantity-input" type="number"
								id="quantityInput-<%= product.getId() %>" value="0" min="0"
								onchange="updateQuantity(<%= product.getId() %>)">
							<button class="quantity-btn" type="button"
								onclick="increaseQuantity(<%= product.getId() %>)">+</button>
						</div>
						<% } %>


						<!-- Hidden input to store the product ID and selected quantity -->
						<input type="hidden" name="cartItems[<%= product.getId() %>]"
							id="quantity-<%= product.getId() %>" value="0">
					</div>
					<% 
                } 
            } else { 
        %>
					<p>No products available at the moment.</p>
					<% } %>
				</div>

				<!-- Submit Button to add items to the cart -->
				<% if (user == null || !user.getIsAdmin()) { %>
				<!-- Only for non-admins -->
				<div class="actions">
					<button type="submit" class="btn btn-primary">Go to Cart</button>
				</div>
				<%} %>
			</form>

		</main>
	</div>
</body>
</html>
