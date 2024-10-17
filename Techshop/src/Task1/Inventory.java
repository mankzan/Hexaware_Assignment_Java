public class Inventory {
    private int inventoryID;
    private Product product;  // Composition
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
        // Logic to add specified quantity to inventory
        this.quantityInStock += quantity;
    }

    public void removeFromInventory(int quantity) {
        // Logic to remove specified quantity from inventory
        this.quantityInStock -= quantity;
    }

    public void updateStockQuantity(int newQuantity) {
        // Logic to update stock quantity
        this.quantityInStock = newQuantity;
    }

    public boolean isProductAvailable(int quantityToCheck) {
        // Logic to check product availability
        return this.quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        // Logic to calculate the total inventory value
        return product.getPrice() * quantityInStock;
    }

    public void listLowStockProducts(int threshold) {
        // Logic to list products below the threshold
    }

    public void listOutOfStockProducts() {
        // Logic to list out-of-stock products
    }
}
