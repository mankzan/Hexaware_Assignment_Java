package Task1;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    // Constructor
    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Methods
    public int calculateTotalOrders() {
        // Logic to calculate total orders placed by this customer
    }

    public void getCustomerDetails() {
        // Logic to display customer details
    }

    public void updateCustomerInfo(String email, String phone, String address) {
        // Logic to update email, phone, and address
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
