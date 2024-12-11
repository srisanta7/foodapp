<%@page import="com.foodApp.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to FoodApp</title>
    <link rel="stylesheet" type="text/css" href="/views/style.css">
</head>
<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <%@ include file="sidebar.jsp" %>
    </div>

    <!-- Main Container for Header and Content -->
    <div class="main-container">
        <!-- Header Section -->
        <header class="header">
            <h1>Welcome to FoodApp</h1>
            <input type="text" placeholder="Search for dishes..." class="search-bar">
        </header>

        <!-- Main Content Section -->
        <div class="content">
            <!-- Featured Categories Section -->
            <section class="featured-section">
                <h2>Featured Categories</h2>
                <div class="category-container">
                    <div class="category-card">Pizza</div>
                    <div class="category-card">Burgers</div>
                    <div class="category-card">Desserts</div>
                    <div class="category-card">Drinks</div>
                    <div class="category-card">Biriyani</div>
                    <div class="category-card">kebabs</div>
                    <div class="category-card">chicken wings</div>
                    <div class="category-card">pav bhaji</div>
                </div>
            </section>

            <!-- Popular Items Section -->
            <section class="popular-section">
                <h2>Popular Items</h2>
                <div class="popular-container">
                    <div class="item-card">
                        <img src="/images/pizza.jpg" alt="Pizza">
                        <p>Margherita Pizza</p>
                        <span>$10.99</span>
                    </div>
                    <div class="item-card">
                        <img src="/images/burger.jpg" alt="Burger">
                        <p>Classic Burger</p>
                        <span>$8.99</span>
                    </div>
                    <div class="item-card">
                        <img src="/images/chocolate.jpg" alt="Dessert">
                        <p>Chocolate Cake</p>
                        <span>$5.99</span>
                    </div>
                    <div class="item-card">
                        <img src="/images/loaded.jpg" alt="Pizza">
                        <p>stuffed Pizza</p>
                        <span>$10.99</span>
                    </div>
                    <div class="item-card">
                        <img src="/images/chicken.jpg" alt="Burger">
                        <p>chicken Burger</p>
                        <span>$8.99</span>
                    </div>
                    <div class="item-card">
                        <img src="/images/cake.jpg" alt="Dessert">
                        <p>cheese Cake</p>
                        <span>$5.99</span>
                    </div>
                </div>
            </section>
        </div>
    </div>
</body>
</html>
