import java.util.Date;

import Task1.Product;

public class Inventory {
    private int inventoryID;
    private Product product;  // Composition relationship with Product
    private int quantityInStock;
    private Date lastStockUpdate;

    // Constructor
    public Inventory(int inventoryID, Product product, int quantityInStock, Date lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.product = product;  // Establishing composition
        setQuantityInStock(quantityInStock);  // Using setter to validate stock quantity
        this.lastStockUpdate = lastStockUpdate;
    }

    // Getters and Setters with validation
    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        } else {
            System.out.println("Quantity in stock cannot be negative.");
        }
    }

    public Date getLastStockUpdate() {
        return lastStockUpdate;
    }

    public void setLastStockUpdate(Date lastStockUpdate) {
        this.lastStockUpdate = lastStockUpdate;
    }

    // Additional methods
    public void addToInventory(int quantity) {
        setQuantityInStock(quantityInStock + quantity);
        // Update last stock update date
        this.lastStockUpdate = new Date();
    }

    public void removeFromInventory(int quantity) {
        if (quantityInStock >= quantity) {
            setQuantityInStock(quantityInStock - quantity);
            // Update last stock update date
            this.lastStockUpdate = new Date();
        } else {
            System.out.println("Insufficient stock to remove.");
        }
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }
}
