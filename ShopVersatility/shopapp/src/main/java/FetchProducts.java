import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetchProducts {
	public static void displayProducts(List<Product> products) {
        System.out.println("Product List:");
        System.out.printf("%-10s%-20s%-15s%-10s%-30s%-20s%-10s%-15s%-20s%-30s\n",
                "ID", "Name", "Category", "Price", "Description", "Warranty",
                "Ratings", "Total Purchased", "Seller Username", "Image URL");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Product product : products) {
            System.out.printf("%-10s%-20s%-15s%-10.2f%-30s%-20s%-10.2f%-15d%-20s%-30s\n",
                    product.getId(), product.getName(), product.getCategory(), product.getPrice(),
                    product.getDescription(), product.getWarranty(), product.getRatings(),
                    product.getTotalPurchased(), product.getSellerUsername(), product.getImageUrl());
        }
    }

    public static List<Product> getAllProducts(String wishlist) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM products";
            if(wishlist!="")
            {   if(wishlist.startsWith("!"))
                {
            		wishlist=wishlist.substring(1);
            		System.out.println("WMMMMMMMMMMM:"+wishlist);
            		sql = "SELECT * FROM products where name like '%"+wishlist+"%';";
                }
                else
                {
                	sql = "SELECT * FROM products where id in (select productid as id from wishlist where userid='"+wishlist+"');";
                }
                
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = createProductFromResultSet(resultSet);
                        productList.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch products: " + e.getMessage());
        }
        
        

        return productList;
    }


    private static Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String category = resultSet.getString("category");
        double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        String warranty = resultSet.getString("warranty");
        double ratings = resultSet.getDouble("ratings");
        int totalPurchased = resultSet.getInt("total_purchased");
        String sellerUsername = resultSet.getString("seller_username");
        String imageUrl = resultSet.getString("image_url");

        // Replace null attributes with empty strings
        name = (name == null) ? "" : name;
        category = (category == null) ? "" : category;
        description = (description == null) ? "" : description;
        warranty = (warranty == null) ? "" : warranty;
        sellerUsername = (sellerUsername == null) ? "" : sellerUsername;
        imageUrl = (imageUrl == null) ? "" : imageUrl;

        return new Product(id, name, category, price, description, warranty, ratings, totalPurchased, sellerUsername, imageUrl);
    }
}
