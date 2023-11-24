<!-- signup.jsp -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration - Versatility Shopping</title>
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

        .signup-form {
            width: 30%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .signup-form label,
        .signup-form input,
        .signup-form select {
            display: block;
            margin: 10px 0;
            width: 100%;
            box-sizing: border-box;
        }

        .signup-form button {
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .signup-form button:hover {
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
        <div class="signup-form">
            <h2>User Registration</h2>
            <form action="AddUserServlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>

                <label for="pincode">Pincode:</label>
                <input type="text" id="pincode" name="pincode" required>

                <label for="mobileNumber">Mobile Number:</label>
                <input type="text" id="mobileNumber" name="mobileNumber" required>

                <label for="userType">Type:</label>
                <select id="userType" name="userType">
                    <option value="buyer">Buyer</option>
                    <option value="seller">Seller</option>
                </select>

                <button type="submit">Register</button>
            </form>
        </div>
    </div>
</body>

</html>
