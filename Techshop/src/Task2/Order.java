import java.util.Date;

public class Order {
    private int orderID;
    private Customer customer;  // Composition - Customer who placed the order
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
        // Logic to calculate total amount for this order
        return totalAmount; // Returning the total amount
    }

    public void getOrderDetails() {
        System.out.println("OrderID: " + orderID);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Customer Details: ");
        customer.getCustomerDetails();
    }

    public void updateOrderStatus(String status) {
        // Logic to update order status
    }

    public void cancelOrder() {
        // Logic to cancel the order and adjust stock levels
    }
}
