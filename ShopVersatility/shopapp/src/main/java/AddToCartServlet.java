import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("+++++++++++++++");
        String productId = request.getParameter("productId");
        String userId = request.getParameter("userId");  // Assuming you have a userId parameter, adjust as needed
        System.out.println("))))"+productId+"}}"+userId);
        // Update the SQL query based on your database schema
        String addToCartQuery = "INSERT INTO cart (productId, userId) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement addToCartStatement = connection.prepareStatement(addToCartQuery)) {
                addToCartStatement.setString(1, productId);
                addToCartStatement.setString(2, userId);
                addToCartStatement.executeUpdate();
            }

            response.getWriter().println("Product added to cart successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred. Please check the server logs for details.");
        }
    }
}
