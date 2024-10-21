package com.hexaware.ecommerceapp.entity;

public class Order {
	int Orders_id;
	int Customer_id;
	String Order_date;
	Double Total_price;
	String Shipping_address;
	
	public Order() {
		
	}
	
	
	public Order(int orders_id, int customer_id, String order_date, Double total_price, String shipping_address) {
		super();
		Orders_id = orders_id;
		Customer_id = customer_id;
		Order_date = order_date;
		Total_price = total_price;
		Shipping_address = shipping_address;
	}
	
	@Override
	public String toString() {
		return "Orders [Orders_id=" + Orders_id + ", Customer_id=" + Customer_id + ", Order_date=" + Order_date
				+ ", Total_price=" + Total_price + ", Shipping_address=" + Shipping_address + "]";
	}

	public int getOrders_id() {
		return Orders_id;
	}
	public void setOrders_id(int orders_id) {
		Orders_id = orders_id;
	}
	public int getCustomer_id() {
		return Customer_id;
	}
	public void setCustomer_id(int customer_id) {
		Customer_id = customer_id;
	}
	public String getOrder_date() {
		return Order_date;
	}
	public void setOrder_date(String order_date) {
		Order_date = order_date;
	}
	public Double getTotal_price() {
		return Total_price;
	}
	public void setTotal_price(Double total_price) {
		Total_price = total_price;
	}
	public String getShipping_address() {
		return Shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		Shipping_address = shipping_address;
	}
	
	
}