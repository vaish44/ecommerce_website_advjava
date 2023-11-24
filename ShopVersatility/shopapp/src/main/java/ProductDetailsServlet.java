import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");

        // Fetch product details by productId (you need to implement this method)
        Product product = getProductDetails(productId);

        // Set product details as a request attribute
        request.setAttribute("product", product);

        // Forward the request to productdetails.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("productdetails.jsp");
        dispatcher.forward(request, response);
    }

    // You need to implement this method to fetch product details from the database
    private Product getProductDetails(String productId) {
        Product product = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id = ?")) {
            preparedStatement.setString(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getString("id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setDescription(resultSet.getString("description"));
                    product.setWarranty(resultSet.getString("warranty"));
                    product.setRatings(resultSet.getDouble("ratings"));
                    product.setTotalPurchased(resultSet.getInt("totalPurchased"));
                    product.setSellerUsername(resultSet.getString("sellerUsername"));
                    product.setImageUrl(resultSet.getString("imageUrl"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return product;
    }

}
