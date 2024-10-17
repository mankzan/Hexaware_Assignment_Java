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
        setTotalAmount(totalAmount);  // Using setter to validate total amount
    }

    // Getters and Setters with validation
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

    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        } else {
            System.out.println("Total amount cannot be negative.");
        }
    }

    // Other methods (same as Task 2)
    public double calculateTotalAmount() {
        return totalAmount;
    }

    public void getOrderDetails() {
        System.out.println("OrderID: " + orderID);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: " + totalAmount);
        customer.getCustomerDetails();
    }

    public void updateOrderStatus(String status) {
    }

    public void cancelOrder() {
    }
}
