public class OrderDetail {
    private int orderDetailID;
    private Order order;  // Composition relationship with Order
    private Product product;  // Composition relationship with Product
    private int quantity;

    // Constructor
    public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;  // Establishing composition
        this.product = product;  // Establishing composition
        setQuantity(quantity);  // Using setter to validate quantity
    }

    // Getters and Setters
    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity must be a positive integer.");
        }
    }

    // Calculate subtotal based on product price and quantity
    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

    public void getOrderDetailInfo() {
        System.out.println("OrderDetailID: " + orderDetailID);
        System.out.println("Product: " + product.getProductName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: " + calculateSubtotal());
    }

    public void updateQuantity(int newQuantity) {
        setQuantity(newQuantity);  // Using setter to validate new quantity
    }

    public void addDiscount(double discount) {
        // Logic to add discount can be implemented here
    }
}
