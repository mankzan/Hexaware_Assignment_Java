public class OrderDetail {
    private int orderDetailID;
    private Order order;  // Composition
    private Product product;  // Composition
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
        // Logic to calculate the subtotal
        return product.getPrice() * quantity;
    }

    public void getOrderDetailInfo() {
        // Logic to display order detail info
    }

    public void updateQuantity(int newQuantity) {
        // Logic to update the quantity
        this.quantity = newQuantity;
    }

    public void addDiscount(double discount) {
        // Logic to apply a discount
    }
}
