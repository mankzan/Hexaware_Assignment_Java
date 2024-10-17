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
        setPrice(price);  // Using setter to validate price
    }

    // Getters and Setters with validation
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    // Other methods (same as Task 2)
    public void getProductDetails() {
        System.out.println("ProductID: " + productID);
        System.out.println("Product Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
    }

    public void updateProductInfo(String description, double price) {
        setDescription(description);
        setPrice(price);
    }

    public boolean isProductInStock() {
        return true;
    }
}
