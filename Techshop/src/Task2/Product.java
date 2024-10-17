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
        System.out.println("ProductID: " + productID);
        System.out.println("Product Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
    }

    public void updateProductInfo(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public boolean isProductInStock() {
        // Logic to check if the product is in stock
        return true; // Dummy return for now
    }
}
