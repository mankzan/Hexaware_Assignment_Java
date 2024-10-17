package Task6;

import java.util.SortedMap;
import java.util.TreeMap;

import Task1.Order;
import Task1.OrderDetail;
import Task2.Inventory;

public class InventoryManager {
    private SortedMap<Integer, Inventory> inventoryMap = new TreeMap<>();

    // Method to add or update inventory
    public void addOrUpdateInventory(Inventory inventory) {
        inventoryMap.put(inventory.getProduct().getProductID(), inventory);
    }

    // Method to get inventory info
    public Inventory getInventory(int productID) {
        return inventoryMap.get(productID);
    }
    
    public void processOrder(Order order) throws InsufficientStockException {
        for (OrderDetail detail : order.getOrderDetails()) { // Assuming Order has a method to get order details
            Inventory inventory = inventoryManager.getInventory(detail.getProduct().getProductID());
            inventory.removeFromInventory(detail.getQuantity()); // This method throws InsufficientStockException
        }
    }
    
    public List<Product> searchProducts(String searchCriteria) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(searchCriteria.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }


}
