package shoppingapplication;

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String warranty;
    private double ratings;
    private int totalPurchased;
    private String sellerUsername;
    private String imageUrl;

    // Constructors, getters, and setters

    public Product(String id, String name, String category, double price, String description, String warranty,
                   double ratings, int totalPurchased, String sellerUsername, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.warranty = warranty;
        this.ratings = ratings;
        this.totalPurchased = totalPurchased;
        this.sellerUsername = sellerUsername;
        this.imageUrl = imageUrl;
    }

    // Getters and setters for all attributes

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public int getTotalPurchased() {
        return totalPurchased;
    }

    public void setTotalPurchased(int totalPurchased) {
        this.totalPurchased = totalPurchased;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
