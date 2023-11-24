//AddUserServlet.java
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Received request to add user: " + username);

        try
        {
            Connection connection = DBConnection.getConnection();
            String sql = "INSERT INTO users (userid, password, name, address, pincode, mobile_number, user_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql))
            {
	           statement.setString(1, username);
	           statement.setString(2, password);
	           statement.setString(3, request.getParameter("name"));
	           statement.setString(4, request.getParameter("address"));
	           statement.setString(5, request.getParameter("pincode"));
	           statement.setString(6, request.getParameter("mobileNumber"));
	           statement.setString(7, request.getParameter("userType"));
	
	           int rowsAffected = statement.executeUpdate();
	           System.out.println("Rows affected: " + rowsAffected);
            }
            connection.close();
            System.out.println("User added successfully: " + username);
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute SQL query: " + e.getMessage());
        }

        response.sendRedirect("signup.jsp");
    }
}
