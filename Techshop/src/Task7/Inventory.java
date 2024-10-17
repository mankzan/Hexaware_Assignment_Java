package Task7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inventory {
    private DatabaseConnector dbConnector;

    public Inventory() {
        this.dbConnector = new DatabaseConnector();
    }

    // Reduce stock when an order is placed
    //5: Inventory Management
    public void reduceStock(int productID, int quantity) throws InsufficientStockException, SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "SELECT QuantityInStock FROM Inventory WHERE ProductID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int stock = rs.getInt("QuantityInStock");
                if (stock < quantity) {
                    throw new InsufficientStockException("Insufficient stock for product ID: " + productID);
                }
                // Reduce stock if sufficient
                String updateQuery = "UPDATE Inventory SET QuantityInStock = QuantityInStock - ? WHERE ProductID = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, productID);
                    updateStmt.executeUpdate();
                }
            } else {
                throw new InsufficientStockException("Product not found in inventory: " + productID);
            }
        } finally {
            dbConnector.closeConnection();
        }
    }


}

