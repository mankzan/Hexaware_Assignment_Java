package com.hexaware.ecommerceapp.entity;

public class Cart {
	String Cart_id;
	int Customer_id;
	int Product_id;
	int Quantity;
	
	public Cart() {
		
	}
	
	public Cart(String cart_id, int customer_id, int product_id, int quantity) {
		super();
		Cart_id = cart_id;
		Customer_id = customer_id;
		Product_id = product_id;
		Quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Cart [Cart_id=" + Cart_id + ", Customer_id=" + Customer_id + ", Product_id=" + Product_id
				+ ", Quantity=" + Quantity + "]";
	}


	public String getCart_id() {
		return Cart_id;
	}
	public void setCart_id(String cart_id) {
		Cart_id = cart_id;
	}
	public int getCustomer_id() {
		return Customer_id;
	}
	public void setCustomer_id(int customer_id) {
		Customer_id = customer_id;
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