package com.hexaware.ecommerceapp.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexaware.ecommerceapp.entity.Customer;
import com.hexaware.ecommerceapp.entity.Product;
import com.hexaware.ecommerceapp.exception.CustomerNotFoundException;
import com.hexaware.ecommerceapp.exception.OrderNotFoundException;
import com.hexaware.ecommerceapp.exception.ProductNotFoundException;
import com.hexaware.ecommerceapp.service.EcommerceServiceImp;
import com.hexaware.ecommerceapp.Util.*;

/**
 * Implementation of the Order Processor Repository interface.
 * 
 * Provides methods for managing customers, products, carts, and orders.
 * 
 * Handles database operations and exception handling.
 * 
 ** orderId = customerId * productId;
 ** orderItemId = orderId * 13 +9;
 ** cartId = customerId * 3 + 2; 
 * 
 * Date : 18/10/24
 * 
 */

public class OrderProcessorRepositoryImpl implements IOrderProcessorRepository{
	private Connection con;
	PreparedStatement stat;
	public OrderProcessorRepositoryImpl() {
		con=DBConnect.getConnect();
	}
	@Override
	public boolean createProduct(Product product) {
		try
		{
	  stat=con.prepareStatement("insert into Products values(?,?,?,?,?)");
	  stat.setInt(1, product.getProduct_id());
	  stat.setString(2, product.getName());
	  stat.setDouble(3, product.getPrice());
	  stat.setString(4, product.getDescription());
	  stat.setInt(5, product.getStockQuantity());
	  stat.executeUpdate();
	  System.out.println("product created");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean createCustomer(Customer customer) {
		try
		{
			boolean flag = false;
		 flag =	EcommerceServiceImp.validateCustomer(customer);
		 
		 if(flag==true)
		 {
	  stat=con.prepareStatement("insert into Customers values(?,?,?,?)");
	  stat.setInt(1, customer.getCustomer_id());
	  stat.setString(2, customer.getName());
	  stat.setString(3, customer.getEmail());
	  stat.setString(4, customer.getPassword());
	  
	  stat.executeUpdate();
	  System.out.println("customer added");
		 }
		 else
		 {
			 System.out.println("invalid customer data....");
		 }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) throws ProductNotFoundException{
		boolean found=false;
		try
		{
		stat=con.prepareStatement("delete from order_items where Product_id=?");
		stat.setInt(1, productId);
        int sqlReturnOrderItem = stat.executeUpdate();
		stat=con.prepareStatement("delete from cart where Product_id=?");
		stat.setInt(1, productId);
        int sqlReturnCart = stat.executeUpdate();
		stat=con.prepareStatement("delete from Products where Product_id=?");
        stat.setInt(1, productId);
        int sqlReturn = stat.executeUpdate();
        if(sqlReturn==0) {
        	throw new ProductNotFoundException("product not found");
        }
        else {
        	found=true;
        	System.out.println("product removed");
        }
		}
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
        }


		return false;
	}

	@Override
	public boolean deleteCustomer(int customerId) throws CustomerNotFoundException{
		try
		{
		stat=con.prepareStatement("delete from Customers where Customer_id=?");
        stat.setInt(1, customerId);
        int sqlReturn=stat.executeUpdate();
        if(sqlReturn==0) {
        	throw new CustomerNotFoundException("customer not found");
        }
        else {
        	System.out.println("customer removed");
        }
		}
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean addToCart(Customer customer, Product product, int quantity) {
		try
		{
	  stat=con.prepareStatement("insert into cart values(?,?,?,?)");
	  int cartId=  customer.getCustomer_id() * 3 + 2;
	  stat.setInt(1,cartId);
	  stat.setInt(2, customer.getCustomer_id());
	  stat.setInt(3, product.getProduct_id());
	  stat.setInt(4, quantity);
	  
	  stat.executeUpdate();
	  System.out.println("product added to cart");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean removeFromCart(Customer customer, Product product) {
		try
		{
		stat=con.prepareStatement("delete from cart where Cart_id=?");
		int cartId=  customer.getCustomer_id() * 3 + 2;
		  stat.setInt(1,cartId);
        stat.executeUpdate();
        System.out.println("removed from cart");
		}
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public List<Product> getAllFromCart(Customer customer) {
		try
		{
		
		stat=con.prepareStatement("select product_id from cart where customer_id=? "); 
		stat.setInt(1,customer.getCustomer_id());
        ResultSet rs = stat.executeQuery();//list of product_id
        List<Integer> product_ids = new ArrayList();
        
        while(rs.next())
        {
        	product_ids.add(rs.getInt(1));
        }
        List<Product> products = new ArrayList();
        for(int i : product_ids) {
        	products.add(getProductById(i));
        }
        return products;
        }
        catch(Exception e)
		{
			System.out.println(e);
			
		}
		return null;
	}

	@Override
	public boolean placeOrder(Customer customer, List<Map<Product, Integer>> productQuantityList, String shippingAddress) {
//		System.out.println("dao placeorder");
		LocalDate currentDate = LocalDate.now(); 
		Double totalAmount=0.0;
		boolean shouldUpdate=false;
		int proId = 999;
		for(Map<Product, Integer> i: productQuantityList) {
			for(Map.Entry<Product, Integer> entry : i.entrySet()) {
				Product p = entry.getKey();
				int quantity = entry.getValue();
				
				if(p.getStockQuantity()>=quantity) {
					shouldUpdate=true;
					proId = p.getProduct_id();
					totalAmount+=p.getPrice()*quantity;
				}
				else {
					System.out.println(p.getProduct_id()+" is not in stock");
					break;
				}
			}
		}
		
		if(shouldUpdate==true) {
			int orderId = customer.getCustomer_id()  * proId;
//			System.out.println("update order table");
			boolean updatedOrderTable = updateOrderTable(orderId, customer.getCustomer_id(),currentDate.toString(),
					totalAmount, shippingAddress);
			
			for(Map<Product, Integer> i: productQuantityList) {
				System.out.println(i);
				for(Map.Entry<Product, Integer> entry : i.entrySet()) {
					Product p = entry.getKey();
					int quantity = entry.getValue();
					int orderItemId = (orderId * 13) + 9;
//					System.out.println(orderItemId);
					boolean updatedOrderItemTable = updateOrderItemTable(orderItemId, orderId, 
							p.getProduct_id(), quantity);
					}
				}
			
			return true;
			
		}
		
		return false;
	}

	
	public Customer getCustomerById(int customer_id) {
		Customer customer = new Customer();
		try
		{
		
		stat=con.prepareStatement("select * from Customers where Customer_id=? ");
		stat.setInt(1,customer_id);
        ResultSet rs = stat.executeQuery();
        while(rs.next())
        {
        	customer.setCustomer_id(rs.getInt(1));
        	customer.setName(rs.getString(2));
        	customer.setEmail(rs.getString(3));
        	customer.setPassword(rs.getString(4));
        	return customer;
        }
        
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		return null;
	}
	
	public Product getProductById(int product_id) {
		Product product = new Product();
		try
		{
		
		stat=con.prepareStatement("select * from Products where Product_id=? ");
		stat.setInt(1,product_id);
        ResultSet rs = stat.executeQuery();
        while(rs.next())
        {
        	product.setProduct_id(rs.getInt(1));
        	product.setName(rs.getString(2));
        	product.setPrice(rs.getDouble(3));
        	product.setDescription(rs.getString(4));
        	product.setStockQuantity(rs.getInt(5));
        	
        }
        return product;
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		return null;
		
	}
	
	public boolean updateOrderTable(int orderId,int customerId,String orderDate,Double totalAmount,String shippingAddress) {
		try
		{
	  stat=con.prepareStatement("insert into orders values(?,?,?,?,?)");
	  stat.setInt(1, orderId);
	  stat.setInt(2, customerId);
	  stat.setString(3, orderDate);
	  stat.setDouble(4, totalAmount);
	  stat.setString(5, shippingAddress);
	  stat.executeUpdate();
//	  System.out.println("order updated");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public boolean updateOrderItemTable(int orderItemId,int orderId,int productId,int quantity) {
		try
		{
	  stat=con.prepareStatement("insert into Order_Items values(?,?,?,?)");
	  stat.setInt(1, orderItemId);
	  stat.setInt(2, orderId);
	  stat.setInt(3, productId);
	  stat.setInt(4, quantity);
	  stat.executeUpdate();
	  System.out.println("Your order has been placed"+" " + orderItemId);
//	  System.out.println("order item updated");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public List<Map<Product,Integer>> getProductQuantityFromCart(Customer customer) {
		try
		{
		stat=con.prepareStatement("select product_id, quantity from cart where customer_id=? "); 
		stat.setInt(1,customer.getCustomer_id());
        ResultSet rs = stat.executeQuery();
        List<Integer> product_ids = new ArrayList();
        
        List<Map<Product, Integer>> productQuantityMapList = new ArrayList();
        
        while(rs.next())
        {
        	Map<Product, Integer> productQuantityMap = new HashMap();
        	Product p = getProductById(rs.getInt(1));
        	productQuantityMap.put(p, rs.getInt(2));
        	productQuantityMapList.add(productQuantityMap);
        }
        
        return productQuantityMapList;
        }
        catch(Exception e)
		{
			System.out.println(e);
			
		}
		return null;
	}
	
	public List<Map<Product, Integer>> getOrdersByCustomer(int customer_id)throws CustomerNotFoundException,OrderNotFoundException{
		
		try
		{
		stat=con.prepareStatement(" select oi.product_id, oi.quantity from orders o inner join order_items oi on o.order_id = oi.order_id where o.customer_id = ?"); 
		stat.setInt(1,customer_id);
        ResultSet rs = stat.executeQuery();
                
        List<Integer> product_ids = new ArrayList();
        boolean found=false;
        List<Map<Product, Integer>> productQuantityMapList = new ArrayList();
        while(rs.next())
        {
        	found=true;
        	Map<Product, Integer> productQuantityMap = new HashMap();
        	Product p = getProductById(rs.getInt(1));
        	productQuantityMap.put(p, rs.getInt(2));
        	productQuantityMapList.add(productQuantityMap);
        }
        if(found==false) {
        	if (getCustomerById(customer_id)==null) {
                throw new CustomerNotFoundException("Customer with ID " + customer_id + " not found.");
            } else {
                throw new OrderNotFoundException("No orders found for customer with ID " + customer_id);
            }
        }
        return productQuantityMapList;
        }
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
        catch(OrderNotFoundException e)
		{
			System.out.println(e.getMessage());
			
		}
		
		
		return null;
		
	}
	
	
}