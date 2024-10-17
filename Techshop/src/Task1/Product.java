public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;

    // Constructor
    public Product(int productID, String productName, String description, double price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    // Methods
    public void getProductDetails() {
        // Logic to display product details
    }

    public void updateProductInfo(String description, double price) {
        // Logic to update product info
        this.description = description;
        this.price = price;
    }

    public boolean isProductInStock() {
        // Logic to check if the product is in stock
    }
}
