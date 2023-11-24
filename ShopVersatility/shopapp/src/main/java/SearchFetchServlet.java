import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchFetchServlet")
public class SearchFetchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

        	String search = request.getParameter("search");
            String userId = (String) request.getSession().getAttribute("username");
            search="!"+search;
            System.out.println("----"+search+")))"+userId);
            List<Product> productList = FetchProducts.getAllProducts(search);
            FetchProducts.displayProducts(productList);

            // Set the product list as a request attribute
            request.setAttribute("productList", productList);
            

            // Set the username as a request attribute
            request.setAttribute("userId", userId);

            // Fetch cart item count for the user
            int cartItemCount = getCartItemCount(userId);
            System.out.println(cartItemCount);            // Set the cart item count as a request attribute
            request.setAttribute("cartItemCount", cartItemCount);

            // Forward the request to shop.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("shop.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred. Please check the server logs for details.");
        }
    }

    // Example method to get cart item count using DBConnection
    private int getCartItemCount(String userId) {
        int cartItemCount = 0;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM cart WHERE userId = ? and paid = false")) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cartItemCount = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return cartItemCount;
    }
}
