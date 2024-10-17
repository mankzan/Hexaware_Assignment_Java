public class OrderDetail {
    private int orderDetailID;
    private Order order;  // Composition - The order this detail belongs to
    private Product product;  // Composition - The product included in the order detail
    private int quantity;

    // Constructor
    public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    // Methods
    public double calculateSubtotal() {
        // Logic to calculate the subtotal for this order detail
        return product.getPrice() * quantity;
    }

    public void getOrderDetailInfo() {
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product.getProductName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: " + calculateSubtotal());
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void addDiscount(double discount) {
        // Logic to apply discount
    }
}
