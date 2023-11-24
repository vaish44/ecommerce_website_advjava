import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve the user ID from the session
            String userId = (String) request.getSession().getAttribute("username");

            // Fetch the cart items for the user from the database
            List<CartItem> cartItems = getCartItems(userId);

            // Set the cart items as a request attribute
            request.setAttribute("cartItems", cartItems);
            
            request.setAttribute("userId", userId);

            // Forward the request to cart.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred. Please check the server logs for details.");
        }
    }

    private List<CartItem> getCartItems(String userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT products.id as product_id, products.name as product_name, price, COUNT(*) as quantity FROM cart,products WHERE paid = false and userid = ? and cart.productid=products.id GROUP BY products.id";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String productId = resultSet.getString("product_id");
                        String productName = resultSet.getString("product_name");
                        float price = resultSet.getFloat("price");
                        int quantity = resultSet.getInt("quantity");

                        CartItem cartItem = new CartItem();
                        cartItem.setProductId(productId);
                        cartItem.setProductName(productName);
                        cartItem.setPrice(price);
                        cartItem.setQuantity(quantity);

                        cartItems.add(cartItem);
                    }
                }
            }
        }

        return cartItems;
    }
}
