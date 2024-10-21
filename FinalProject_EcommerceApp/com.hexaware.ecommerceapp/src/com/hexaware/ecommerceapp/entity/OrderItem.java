package com.hexaware.ecommerceapp.entity;

public class OrderItem {
	int Order_item_id;
	int Order_id;
	int Product_id;
	int Quantity;
	
	public OrderItem() {
		
	}
	
	public OrderItem(int order_item_id, int order_id, int product_id, int quantity) {
		super();
		Order_item_id = order_item_id;
		Order_id = order_id;
		Product_id = product_id;
		Quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItems [Order_item_id=" + Order_item_id + ", Order_id=" + Order_id + ", Product_id=" + Product_id
				+ ", Quantity=" + Quantity + "]";
	}

	public int getOrder_item_id() {
		return Order_item_id;
	}
	public void setOrder_item_id(int order_item_id) {
		Order_item_id = order_item_id;
	}
	public int getOrder_id() {
		return Order_id;
	}
	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}
	public int getProduct_id() {
		return Product_id;
	}
	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
}