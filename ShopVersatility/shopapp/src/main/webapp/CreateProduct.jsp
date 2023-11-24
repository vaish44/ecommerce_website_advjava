<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
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

        .create-product-form {
            width: 50%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
        }

        .create-product-form label,
        .create-product-form input,
        .create-product-form button,
        .create-product-form textarea {
            display: block;
            margin: 10px 0;
            width: 100%;
            box-sizing: border-box;
        }

        .create-product-form button {
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .create-product-form button:hover {
            background-color: #555;
        }
    </style>
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
        <div class="create-product-form">
            <h2>Create a New Product</h2>
            <form action="AddProductServlet" method="post">
 
            	<label for="name">Unique Product Id:</label>
                <input type="text" id="id" name="id" required>
            
                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="category">Category:</label>
                <input type="text" id="category" name="category" required>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" step="0.01" required>

                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>

                <label for="warranty">Warranty:</label>
                <input type="text" id="warranty" name="warranty" required>

                <label for="image">Image URL or Upload:</label>
                <input type="text" id="image_url" name="image_url" placeholder="Enter Image URL">

                <button type="submit">Create Product</button>
            </form>
        </div>
    </div>
</body>

</html>
