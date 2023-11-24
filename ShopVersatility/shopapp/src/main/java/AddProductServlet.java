import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {		
    		String id = request.getParameter("id");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            float price = Float.parseFloat(request.getParameter("price"));
            String description = request.getParameter("description");
            String warranty = request.getParameter("warranty");
            String imageUrl = request.getParameter("image_url");
            HttpSession session = request.getSession();
            String sellerUsername = (String) session.getAttribute("username");
            System.out.println("Id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Category: " + category);
            System.out.println("Price: " + price);
            System.out.println("Description: " + description);
            System.out.println("Warranty: " + warranty);
            System.out.println("Image URL: " + imageUrl);
            float ratings = 5;
            int totalPurchased = 0;
            
            try
            {
                Connection connection = DBConnection.getConnection();
                String sql = "INSERT INTO products (id, name, category, price, description, warranty, ratings, total_purchased, seller_username, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql))
                {
                	statement.setString(1, id);
                    statement.setString(2, name);
                    statement.setString(3, category);
                    statement.setFloat(4, price);
                    statement.setString(5, description);
                    statement.setString(6, warranty);
                    statement.setFloat(7, ratings);
                    statement.setInt(8, totalPurchased);
                    statement.setString(9, sellerUsername);
                    statement.setString(10, imageUrl);
                    int rowsAffected = statement.executeUpdate();
     	           	System.out.println("Rows affected: " + rowsAffected);
                }
                connection.close();
                System.out.println("Product added successfully ! ");
            }
            catch (SQLException e) 
            {
                e.printStackTrace();
                throw new RuntimeException("Failed to execute SQL query: " + e.getMessage());
            }
            response.sendRedirect("CreateProduct.jsp");
    }

}
