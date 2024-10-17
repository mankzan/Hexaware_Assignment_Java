import java.util.Date;

public class Inventory {
    private int inventoryID;
    private Product product;  // Composition - Product associated with this inventory
    private int quantityInStock;
    private Date lastStockUpdate;

    // Constructor
    public Inventory(int inventoryID, Product product, int quantityInStock, Date lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

    // Methods
    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void addToInventory(int quantity) {
        this.quantityInStock += quantity;
        this.lastStockUpdate = new Date();  // Update the stock date
    }

    public void removeFromInventory(int quantity) {
        this.quantityInStock -= quantity;
        this.lastStockUpdate = new Date();  // Update the stock date
    }

    public void updateStockQuantity(int newQuantity) {
        this.quantityInStock = newQuantity;
        this.lastStockUpdate = new Date();  // Update the stock date
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return this.quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        return product.getPrice() * quantityInStock;
    }

    public void listLowStockProducts(int threshold) {
        // Logic to list products with quantity below the threshold
    }

    public void listOutOfStockProducts() {
        // Logic to list products that are out of stock
    }
}
