<!-- home.jsp -->
<!DOCTYPE html>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Versatility Shopping</title>
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

        nav a:hover {
            background-color: #555;
        }

        .content {
            padding: 20px;
            text-align: center;
        }

        .search-box {
            margin: 20px auto;
            width: 50%;
            padding: 10px;
            font-size: 16px;
        }

        /* Additional styles for the product creation form */
        .create-product-form {
            display: none;
            margin-top: 20px;
        }

        /* Add some styles for the user info display */
        .user-info {
            position: absolute;
            top: 10px;
            right: 10px;
            color: white;
        }

        /* Add the following style for the product creation link */
        .create-product-link {
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            margin: 0 10px;
            font-size: 18px;
        }

        .create-product-link:hover {
            background-color: #555;
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
    </style>

    <!-- JavaScript to toggle the display of the product creation form -->
    <script>
        function toggleProductForm() {
            var productForm = document.getElementById('create-product-form');
            productForm.style.display = (productForm.style.display === 'none') ? 'block' : 'none';
        }
    </script>
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
        <% String userType = (String)session.getAttribute("userType"); %>
        <% if ("seller".equals(userType)) { %>
            <!-- Display "Create Product" link for sellers -->
            <a href="CreateProduct.jsp" class="create-product-link">Create Product</a>
        <% } %>
    </nav>
    
    

    <div class="content">
        <div class="user-info">
            <%-- Retrieve username and userType from the session --%>
            <% String username = (String)session.getAttribute("username"); %>
            <% if (username != null) { %>
                Welcome, <%= username %>! | <a href="LogoutServlet">Logout</a>
            <% } else { %>
                <a href="login.jsp">Login</a>
            <% } %>
        </div>


		<form action="SearchFetchServlet" method="get">
            <input type="text" name="search">
            <input type="hidden" name="username" value="<%= username %>">
            <button type="submit">SEARCH</button>
        </form>
        
        
   

        <!-- Display product creation form for sellers -->
        <div class="create-product-form" id="create-product-form">
            <h2>Create a New Product</h2>
            <form action="AddProductServlet" method="post">
                <!-- Add product creation form fields here... -->
                <!-- For simplicity, you can reuse the form from CreateProduct.jsp -->
            </form>
        </div>

        <!-- Main content goes here (initially blank) -->
    </div>
    <form action="PurchaseServlet" method="get">
            <!-- Add a hidden input field to pass the username -->
            <input type="hidden" name="username" value="<%= username %>">
            <button type="submit">Purchase Story</button>
        </form><br>
    <form action="HomeServlet" method="get">
            <!-- Add a hidden input field to pass the username -->
            <input type="hidden" name="username" value="<%= username %>">
            <button type="submit">Start Shopping</button>
        </form><br>
      <form action="WishlistFetchServlet" method="get">
            <!-- Add a hidden input field to pass the username -->
            <input type="hidden" name="username" value="<%= username %>">
            <button type="submit">Wishlist Shopping</button>
        </form>
        
        
</body>

</html>
