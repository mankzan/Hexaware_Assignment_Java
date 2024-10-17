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
        setEmail(email);  // Using setter to validate
        setPhone(phone);  // Using setter to validate
        this.address = address;
    }

    // Getters and Setters with validation
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@") && email.contains(".")) {  // Simple email validation
            this.email = email;
        } else {
            System.out.println("Invalid email format.");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d{10}")) {  // Ensure phone has 10 digits
            this.phone = phone;
        } else {
            System.out.println("Invalid phone number. It should contain exactly 10 digits.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Other methods (same as Task 2)
    public int calculateTotalOrders() {
        return 0;
    }

    public void getCustomerDetails() {
        System.out.println("CustomerID: " + customerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }

    public void updateCustomerInfo(String email, String phone, String address) {
        setEmail(email);
        setPhone(phone);
        this.address = address;
    }
}
