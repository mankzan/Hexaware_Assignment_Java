import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();

    // Method to add a new order
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Method to update order status
    public void updateOrderStatus(int orderID, String newStatus) throws Exception {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                order.setStatus(newStatus); // Assuming a setStatus method exists in Order class
                return;
            }
        }
        throw new Exception("Order not found for ID: " + orderID);
    }

    // Method to remove a canceled order
    public void removeOrder(int orderID) throws Exception {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                orders.remove(i);
                return;
            }
        }
        throw new Exception("Order not found for ID: " + orderID);
    }
    
    public void sortOrdersByDate(boolean ascending) {
        if (ascending) {
            Collections.sort(orders, Comparator.comparing(Order::getOrderDate));
        } else {
            Collections.sort(orders, Comparator.comparing(Order::getOrderDate).reversed());
        }
    }
}
