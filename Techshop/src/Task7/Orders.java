package Task7;

import java.sql.*;

public class Orders {
    private int orderID;
    private Customers customer;
    private Date orderDate;
    private double totalAmount;
    
    private DatabaseConnector dbConnector;

    public Orders() {
        this.dbConnector = new DatabaseConnector();
    }

    //3: Placing Customer Orders
    public void placeOrder(Customers customer, List<OrderDetails> orderDetailsList) throws SQLException {
        Connection conn = dbConnector.openConnection();
        try {
            String orderQuery = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES (?, ?, ?)";
            try (PreparedStatement orderStmt = conn.prepareStatement(orderQuery)) {
                orderStmt.setInt(1, customer.getCustomerID());
                orderStmt.setDate(2, new java.sql.Date(new Date().getTime()));
                orderStmt.setDouble(3, calculateTotalAmount(orderDetailsList));
                orderStmt.executeUpdate();
            }

            // Insert order details into OrderDetails table
            for (OrderDetails detail : orderDetailsList) {
                String detailQuery = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
                try (PreparedStatement detailStmt = conn.prepareStatement(detailQuery)) {
                    detailStmt.setInt(1, orderID);
                    detailStmt.setInt(2, detail.getProduct().getProductID());
                    detailStmt.setInt(3, detail.getQuantity());
                    detailStmt.executeUpdate();

                    // Update inventory
                    Inventory inventory = new Inventory();
                    inventory.reduceStock(detail.getProduct().getProductID(), detail.getQuantity());
                }
            }

            System.out.println("Order placed successfully.");
        } catch (SQLException e) {
            throw new SQLException("Order processing failed: " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }

    private double calculateTotalAmount(List<OrderDetails> orderDetailsList) {
        double total = 0;
        for (OrderDetails detail : orderDetailsList) {
            total += detail.getProduct().getPrice() * detail.getQuantity();
        }
        return total;
    }
    
    public void updateOrder(int orderID, String status) throws ConcurrencyException, SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "UPDATE Orders SET Status = ? WHERE OrderID = ? AND Version = ?";  // Version column for optimistic concurrency

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new ConcurrencyException("Order update failed due to concurrent modification.");
            }
        } finally {
            dbConnector.closeConnection();
        }
    }
    
    //8: Payment Processing
    public void processPayment(int orderID, double amount, String paymentMethod) throws SQLException, PaymentFailedException {
        if (amount <= 0) {
            throw new PaymentFailedException("Invalid payment amount.");
        }

        String query = "INSERT INTO Payments (OrderID, Amount, PaymentMethod) VALUES (?, ?, ?)";
        try (Connection conn = dbConnector.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderID);
            stmt.setDouble(2, amount);
            stmt.setString(3, paymentMethod);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new PaymentFailedException("Payment processing failed.");
            }

            // Update order status to 'Paid'
            String updateOrderStatus = "UPDATE Orders SET Status = 'Paid' WHERE OrderID = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateOrderStatus)) {
                updateStmt.setInt(1, orderID);
                updateStmt.executeUpdate();
            }
        }
    }


}
