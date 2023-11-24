<!-- login.jsp -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Versatility Shopping</title>
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

        .login-form {
            width: 30%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .login-form label,
        .login-form input,
        .login-form button {
            display: block;
            margin: 10px 0;
        }

        .login-form button {
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .login-form button:hover {
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
        <div class="login-form">
            <h2>Login</h2>
            <form action="LoginServlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Login</button>
            </form>
        </div>
    </div>
</body>

</html>
