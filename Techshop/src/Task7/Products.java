package Task7;

import java.sql.*;

public class Products {
    private int productID;
    private String productName;
    private String description;
    private double price;

    private DatabaseConnector dbConnector;

    public Products() {
        this.dbConnector = new DatabaseConnector();
    }

    public Products(String productName, String description, double price) throws InvalidDataException {
        if (price < 0) {
            throw new InvalidDataException("Price cannot be negative.");
        }
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.dbConnector = new DatabaseConnector();
    }

    // Add new product with data validation
    //2: Product Catalog Management
    public void addProduct() throws SQLException, InvalidDataException {
        if (productName == null || productName.isEmpty()) {
            throw new InvalidDataException("Product name cannot be empty.");
        }

        Connection conn = dbConnector.openConnection();
        String query = "INSERT INTO Products (ProductName, Description, Price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productName);
            stmt.setString(2, description);
            stmt.setDouble(3, price);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product added successfully.");
            } else {
                throw new InvalidDataException("Failed to add product.");
            }
        } catch (SQLException e) {
            throw new SQLException("Database error: " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }

    // Retrieve product by ID
    public void getProductByID(int productID) throws SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "SELECT * FROM Products WHERE ProductID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("ProductID"));
                System.out.println("Product Name: " + rs.getString("ProductName"));
                System.out.println("Description: " + rs.getString("Description"));
                System.out.println("Price: " + rs.getDouble("Price"));
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error: Unable to retrieve product details - " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }

    // Update product details
    public void updateProduct(int productID, String newDescription, double newPrice) throws SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "UPDATE Products SET Description = ?, Price = ? WHERE ProductID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newDescription);
            stmt.setDouble(2, newPrice);
            stmt.setInt(3, productID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            }
        } finally {
            dbConnector.closeConnection();
        }
    }

    // Delete a product
    public void deleteProduct(int productID) throws SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "DELETE FROM Products WHERE ProductID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productID);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully.");
            }
        } finally {
            dbConnector.closeConnection();
        }
    }
    //9: Product Search and Recommendations
    public List<Product> searchProducts(String keyword) throws SQLException {
        String query = "SELECT * FROM Products WHERE ProductName LIKE ?";
        List<Product> productList = new ArrayList<>();

        try (Connection conn = dbConnector.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Product product = new Product(result.getInt("ProductID"), 
                                              result.getString("ProductName"), 
                                              result.getString("Description"),
                                              result.getDouble("Price"));
                productList.add(product);
            }
        }
        return productList;
    }

}
