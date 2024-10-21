package com.hexaware.ecommerceapp.dao;

import java.util.List;
import java.util.Map;

import com.hexaware.ecommerceapp.entity.Customer;
import com.hexaware.ecommerceapp.entity.Product;
import com.hexaware.ecommerceapp.exception.CustomerNotFoundException;
import com.hexaware.ecommerceapp.exception.OrderNotFoundException;
import com.hexaware.ecommerceapp.exception.ProductNotFoundException;

/**
 * Interface for Order Processor Repository.
 * 
 * Provides methods for managing customers, products, carts, and orders.
 * 
 * Implementations should handle database operations and exception handling.
 * 
 * 
 * Date : 18/10/24
 *
 */



public interface IOrderProcessorRepository {
   
	 boolean createProduct(Product product);

	 boolean createCustomer(Customer customer);

	 boolean deleteProduct(int productId) throws ProductNotFoundException ;

     boolean deleteCustomer(int customerId) throws CustomerNotFoundException;

     boolean addToCart(Customer customer, Product product, int quantity);

     boolean removeFromCart(Customer customer, Product product);

     List<Product> getAllFromCart(Customer customer) throws CustomerNotFoundException , ProductNotFoundException;

     boolean placeOrder(Customer customer, List<Map<Product, Integer>> products, String shippingAddress);

     List<Map<Product, Integer>> getOrdersByCustomer(int customerId) throws CustomerNotFoundException, OrderNotFoundException;
}