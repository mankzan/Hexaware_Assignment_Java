package Task7;

import java.sql.*;

public class Customers {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    private DatabaseConnector dbConnector;

    public Customers() {
        this.dbConnector = new DatabaseConnector();
    }

    public Customers(String firstName, String lastName, String email, String phone, String address) throws InvalidDataException {
        if (!isValidEmail(email)) {
            throw new InvalidDataException("Invalid email format.");
        }
        if (phone.length() != 10) {
            throw new InvalidDataException("Phone number must be 10 digits.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dbConnector = new DatabaseConnector();
    }

    // Method to validate email
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    // Method to register a new customer
    //1: Customer Registration
    public void registerCustomer() throws SQLException, InvalidDataException {
        Connection conn = dbConnector.openConnection();
        String query = "INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer registered successfully.");
            } else {
                throw new InvalidDataException("Failed to register customer.");
            }
        } catch (SQLException e) {
            throw new SQLException("Database error: " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }

    // Method to retrieve customer details by email
    public void getCustomerByEmail(String email) throws SQLException, InvalidDataException {
        if (!isValidEmail(email)) {
            throw new InvalidDataException("Invalid email format.");
        }

        Connection conn = dbConnector.openConnection();
        String query = "SELECT * FROM Customers WHERE Email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("CustomerID"));
                System.out.println("Name: " + rs.getString("FirstName") + " " + rs.getString("LastName"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("Address: " + rs.getString("Address"));
            } else {
                throw new InvalidDataException("Customer not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Database error: " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }

    //7: Customer Account Updates
    public void updateCustomer(int customerID, String newPhone, String newAddress) throws SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "UPDATE Customers SET Phone = ?, Address = ? WHERE CustomerID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPhone);
            stmt.setString(2, newAddress);
            stmt.setInt(3, customerID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer details updated successfully.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error: Unable to update customer details - " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }

    // Method to delete customer
    public void deleteCustomer(int customerID) throws SQLException {
        Connection conn = dbConnector.openConnection();
        String query = "DELETE FROM Customers WHERE CustomerID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerID);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error: Unable to delete customer - " + e.getMessage());
        } finally {
            dbConnector.closeConnection();
        }
    }
}

