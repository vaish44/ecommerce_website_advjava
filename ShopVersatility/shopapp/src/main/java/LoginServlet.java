import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE userid = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Valid user, set session attributes
                    String userType = resultSet.getString("user_type"); // Update here
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("userType", userType);

                    // Redirect based on user type
                    if ("seller".equals(userType)) {
                        response.sendRedirect("home.jsp");
                    } else {
                        response.sendRedirect("home.jsp");
                    }
                } else {
                    // Invalid credentials, redirect to login page
                    response.sendRedirect("login.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Failed to execute SQL query: " + e.getMessage());
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to establish database connection: " + e.getMessage());
        }
    }
}
