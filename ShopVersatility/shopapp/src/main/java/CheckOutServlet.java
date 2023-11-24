import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	String userId = request.getParameter("userId");
        	String shippingAddress = request.getParameter("shippingAddress");
        	String upiId = request.getParameter("upiId");
        	System.out.println("uid::"+userId);
            updateCart(userId,shippingAddress,upiId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("home.jsp");
    }

    private void updateCart(String userId,String shippingAddress,String upiId)
    {
        // Use the DBConnection class to get a database connection
        try (Connection connection = DBConnection.getConnection()) {
            // Use PreparedStatement to prevent SQL injection
            String query = "update cart set paid = true, address = ?, payment = ? WHERE userid = ? and paid = false;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            	preparedStatement.setString(1, shippingAddress);
            	preparedStatement.setString(2, upiId);
                preparedStatement.setString(3, userId);
                // Execute the update
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Cart items paid successfully");
                } else {
                    System.out.println("Cart items not found or payment failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
