package com.hexaware.ecommerceapp.entity;

public class Product {
	int Product_id;
	String Name;
	Double Price;
	String Description;
	int StockQuantity;
	
	public Product() {
		
	}
	
	public Product(int product_id, String name, Double price, String description, int stockQuantity) {
		super();
		Product_id = product_id;
		Name = name;
		Price = price;
		Description = description;
		StockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "Products [Product_id=" + Product_id + ", Name=" + Name + ", Price=" + Price + ", Description="
				+ Description + ", StockQuantity=" + StockQuantity + "]";
	}

	public int getProduct_id() {
		return Product_id;
	}
	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getStockQuantity() {
		return StockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		StockQuantity = stockQuantity;
	}
	
	
}