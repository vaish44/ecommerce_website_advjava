<%@ page import="java.util.List" %>
<%@ page import="shoppingapplication.Product" %>
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
    <title>Versatility Shopping - Shop</title>
    <!-- Add your CSS styles here -->
</head>
<body>
<%
    String userId = (String) request.getAttribute("userId");
%>
<div class="cart-icon" onclick="showCart('<%= userId %>')">
    CART <span id="cartItemCount"><%= request.getAttribute("cartItemCount") %></span>
</div>

    <header>
        <h1>Versatility Shopping</h1>
    </header>

    <nav>
        <a href="home.jsp">Home</a>
        <a href="login.jsp">Login</a>
        <a href="signup.jsp">Signup</a>
        <a href="about.jsp">About</a>
        <% String userType = (String)session.getAttribute("userType"); %>
        <% if ("seller".equals(userType)) { %>
            <a href="CreateProduct.jsp" class="create-product-link">Create Product</a>
        <% } %>
    </nav>
    
    <form action="WishlistFetchServlet" method="get">
            <!-- Add a hidden input field to pass the username -->
            <input type="hidden" name="username" value="<%= userId %>">
            <button type="submit">Wishlist Shopping</button>
        </form>
     <form action="HomeServlet" method="get">
            <!-- Add a hidden input field to pass the username -->
            <input type="hidden" name="username" value="<%= userId %>">
            <button type="submit">Start Shopping</button>
        </form>

    <div class="content">
        <!-- Display product grid -->
        <h2>Featured Products</h2>

        <!-- Display the welcome message if the user ID is available -->
        
        <% if (userId != null) { %>
            <p>Welcome, User ID: <%= userId %>!</p>
        <% } %>

        <div class="grid">
            <c:forEach var="product" items="${productList}">
                <div class="tile">
                    <img src="${product.imageUrl}" style="width:200px;height:200px;" alt="${product.name} ">
                    <h3><a href="ProductDetailsServlet?productId=${product.id}">${product.id}</a></h3>
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p>Price: $${product.price}</p>
                    <!-- Pass the product ID and user ID to addToCart and addToWishlist functions -->
                    <button onclick="addToCart('${product.id}', '${userId}')">Add to Cart</button>
                    <button onclick="Wishlist('${product.id}', '${userId}')">Add to Wishlist</button>
                </div>
            </c:forEach>
        </div>
    </div>
    
    <script>
    var cartItemCount = <%= request.getAttribute("cartItemCount") %>; // Initialize the cart item count

    function addToCart(productId, userId) {
        // Implement logic to add the product to the shopping cart
        // You may use the fetch API to communicate with the server and update the shopping cart state
        fetch('AddToCartServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: '&productId=' + encodeURIComponent(productId) + '&userId=' + encodeURIComponent(userId),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(responseText => {
            alert(responseText);
            // Increment the cart item count when a product is added
            updateCartItemCount(++cartItemCount);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    }

    function updateCartItemCount(count) {
        // Update the cart icon with the new count
        document.getElementById('cartItemCount').innerText = count;
    }

    function showCart(userId) {
        // Redirect to CartServlet with the userId parameter
        window.location.href = 'CartServlet?userId=' + encodeURIComponent(userId);
    }

    function Wishlist(productId, userId) {
        // Implement logic to add the product to the shopping cart
        // You may use the fetch API to communicate with the server and update the shopping cart state
        fetch('WishlistServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'productId=' + encodeURIComponent(productId) + '&userId=' + encodeURIComponent(userId),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(responseText => {
            alert(responseText);
            // Increment the cart item count when a product is added
            updateCartItemCount(++cartItemCount);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    }
</script>


</body>
</html>
