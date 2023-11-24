//Main.java
import java.awt.Desktop;
import java.net.URI;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
    	List<Product> productList = FetchProducts.getAllProducts("");
    	FetchProducts.displayProducts(productList);
    	
        try {
            // Specify the URL of your home page
            URI uri = new URI("http://localhost:8080/shoppingapplication/home.jsp");

            // Open the default web browser
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
