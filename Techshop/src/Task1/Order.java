import java.util.Date;

public class Order {
    private int orderID;
    private Customer customer;  // Composition
    private Date orderDate;
    private double totalAmount;

    // Constructor
    public Order(int orderID, Customer customer, Date orderDate, double totalAmount) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Methods
    public double calculateTotalAmount() {
        // Logic to calculate the total amount for the order
    }

    public void getOrderDetails() {
        // Logic to display order details
    }

    public void updateOrderStatus(String status) {
        // Logic to update order status
    }

    public void cancelOrder() {
        // Logic to cancel the order and adjust stock levels
    }
}
