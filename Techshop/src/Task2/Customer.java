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
        // Logic to calculate total orders placed by the customer
        return 0; // Dummy return for now
    }

    public void getCustomerDetails() {
        System.out.println("CustomerID: " + customerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }

    public void updateCustomerInfo(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
