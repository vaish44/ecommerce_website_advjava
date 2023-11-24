import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WishlistServlet")
public class WishlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String userId = request.getParameter("userId");  // Assuming you have a userId parameter, adjust as needed

        // Update the SQL query based on your database schema
        String addToCartQuery = "INSERT INTO wishlist VALUES (? ,?, ?)";

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement addToCartStatement = connection.prepareStatement(addToCartQuery)) {
            	addToCartStatement.setString(1, productId+"-"+userId);
                addToCartStatement.setString(2, productId);
                addToCartStatement.setString(3, userId);
                addToCartStatement.executeUpdate();
            }

            response.getWriter().println("Product added to Wishlist successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Already in Wishlist");
        }
    }
}
