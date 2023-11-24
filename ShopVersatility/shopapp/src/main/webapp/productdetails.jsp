<%@ page import="shoppingapplication.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Versatility Shopping - Product Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            position: relative;
        }

        header {
            background-color: #333;
            padding: 15px;
            text-align: center;
            color: white;
        }

        nav {
            background-color: #444;
            padding: 10px;
            text-align: center;
        }

        nav a {
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            margin: 0 10px;
            font-size: 18px;
        }

        .content {
            padding: 20px;
            text-align: center;
        }

        img {
            max-width: 100%;
            height: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Versatility Shopping</h1>
    </header>

    <nav>
        <!-- Your navigation links here -->
    </nav>

    <div class="content">
        <h2>Product Details</h2>

        <!-- Display product details -->
        <c:if test="${not empty product}">
            <h3>${product.id}</h3>
            <h3>${product.name}</h3>
            <p><strong>Category:</strong> ${product.category}</p>
            <p><strong>Price:</strong> $${product.price}</p>
            <p><strong>Description:</strong> ${product.description}</p>
            <p><strong>Warranty:</strong> ${product.warranty}</p>
            <p><strong>Ratings:</strong> ${product.ratings}</p>
            <p><strong>Total Purchased:</strong> ${product.totalPurchased}</p>
            <p><strong>Seller:</strong> ${product.sellerUsername}</p>
        </c:if>
        
        <!-- Add back button or link to return to the main page -->
        <a href="shop.jsp">Back to Home</a>
    </div>
</body>
</html>
