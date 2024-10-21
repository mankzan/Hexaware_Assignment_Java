package com.hexaware.ecommerceapp.service;

import com.hexaware.ecommerceapp.entity.Customer;
import com.hexaware.ecommerceapp.exception.CustomerNotFoundException;
import com.hexaware.ecommerceapp.exception.OrderNotFoundException;
import com.hexaware.ecommerceapp.exception.ProductNotFoundException;

public interface IEcommerceService {

	public void createCustomer() ;
	 public void createProduct();
	 public void deleteProduct() throws ProductNotFoundException;
	 public void deleteCustomer() throws CustomerNotFoundException;
	 public void addToCart() ;
	 public void removeFromCart();
	 public void getAllFromCart();
	 public void placeOrder();
	 public void getOrdersByCustomer() throws CustomerNotFoundException, OrderNotFoundException;
	
	 
}