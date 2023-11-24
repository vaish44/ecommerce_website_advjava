<%@ page import="java.util.List" %>
<%@ page import="shoppingapplication.CartItem" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            position: relative; /* Add this to make absolute positioning work */
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

        .grid {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }

        .tile {
            border: 1px solid #ddd;
            padding: 10px;
            margin: 10px;
            text-align: center;
            background-color: #fff;
        }

        .tile img {
            max-width: 100%;
            height: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        button {
            background-color: #555;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #333;
        }
        
       .cart-icon {
            position: absolute;
            top: 10px;
            right: 20px;
            cursor: pointer;
            font-size: 24px;
        } 
    </style>
    <title>Versatility Shopping - Cart</title>
    <!-- Add your CSS styles here -->
</head>
<body>
    <header>
        <h1>Versatility Shopping</h1>
    </header>

    <nav>
        <a href="home.jsp">Home</a>
        <a href="login.jsp">Login</a>
        <a href="signup.jsp">Signup</a>
        <a href="about.jsp">About</a>
    </nav>

    <div class="content">
        <!-- Display cart items -->
        <h2>Your Shopping Story</h2>

        <!-- Display the user ID -->
        <%String userId = (String) request.getAttribute("userId");%>
        <p>User ID: <%= userId %></p>

        <!-- Display cart items and quantities -->
        <c:set var="total" value="0" />
		<c:forEach var="cartItem" items="${cartItems}">
		    <c:set var="total" value="${total + cartItem.price * cartItem.quantity}" />
		    <li>${cartItem.getProductName()} - Price: ${cartItem.getPrice()} - Quantity: ${cartItem.getQuantity()} - Total: ${cartItem.getPrice() * cartItem.getQuantity()}</li>
		</c:forEach>

		<p>Total Cost: ${total}</p>
    </div>

</body>
</html>
