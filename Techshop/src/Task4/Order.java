import java.util.Date;

public class Order {
    private int orderID;
    private Customer customer;  // Composition relationship with Customer
    private Date orderDate;
    private double totalAmount;

    // Constructor
    public Order(int orderID, Customer customer, Date orderDate) {
        this.orderID = orderID;
        this.customer = customer; // Establishing composition
        this.orderDate = orderDate;
        this.totalAmount = 0; // Initialize totalAmount to 0
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Calculate Total Amount based on OrderDetails
    public double calculateTotalAmount(OrderDetail[] orderDetails) {
        totalAmount = 0;  // Reset totalAmount before calculation
        for (OrderDetail detail : orderDetails) {
            totalAmount += detail.calculateSubtotal(); // Sum up the subtotals
        }
        return totalAmount;
    }

    public void getOrderDetails(OrderDetail[] orderDetails) {
        System.out.println("OrderID: " + orderID);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Customer Details:");
        customer.getCustomerDetails();
        System.out.println("Order Details:");
        for (OrderDetail detail : orderDetails) {
            detail.getOrderDetailInfo();
        }
        System.out.println("Total Amount: " + totalAmount);
    }

    public void updateOrderStatus(String status) {
        // Logic to update order status can be implemented here
    }

    public void cancelOrder() {
        // Logic to cancel the order can be implemented here
    }
}
