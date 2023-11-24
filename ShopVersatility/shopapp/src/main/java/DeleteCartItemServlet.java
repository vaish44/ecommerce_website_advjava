import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCartItemServlet")
public class DeleteCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String cartItemId = request.getParameter("cartItemId");
            String userId = request.getParameter("userId");
            // Call a method to delete the cart item by ID from the database
            deleteCartItem(cartItemId,userId);
            System.out.println(cartItemId+"---"+userId);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        
    }

    private void deleteCartItem(String cartItemId, String userId) {
        // Use the DBConnection class to get a database connection
        try (Connection connection = DBConnection.getConnection()) {
            // Use PreparedStatement to prevent SQL injection
            String query = "DELETE FROM cart WHERE productid = ? and userid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, cartItemId);
                preparedStatement.setString(2, userId);
                // Execute the update
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Cart item deleted successfully");
                } else {
                    System.out.println("Cart item not found or deletion failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
