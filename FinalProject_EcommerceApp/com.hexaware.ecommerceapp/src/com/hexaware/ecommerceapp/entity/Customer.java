package com.hexaware.ecommerceapp.entity;

public class Customer {
	int Customer_id;
	String Name;
	String Email;
	String Password;
	
	public Customer() {
		
	}
	
	public Customer(int customer_id, String name, String email, String password) {
		super();
		Customer_id = customer_id;
		Name = name;
		Email = email;
		Password = password;
	}
	
	@Override
	public String toString() {
		return "Customers [Customer_id=" + Customer_id + ", Name=" + Name + ", Email=" + Email + ", Password="
				+ Password + "]";
	}


	public int getCustomer_id() {
		return Customer_id;
	}
	public void setCustomer_id(int customer_id) {
		Customer_id = customer_id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}