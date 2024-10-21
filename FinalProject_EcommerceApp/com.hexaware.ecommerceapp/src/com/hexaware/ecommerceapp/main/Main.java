package com.hexaware.ecommerceapp.main;

import java.util.Scanner;


import com.hexaware.ecommerceapp.entity.Customer;
import com.hexaware.ecommerceapp.exception.CustomerNotFoundException;
import com.hexaware.ecommerceapp.exception.OrderNotFoundException;
import com.hexaware.ecommerceapp.exception.ProductNotFoundException;
import com.hexaware.ecommerceapp.service.IEcommerceService;
import com.hexaware.ecommerceapp.service.EcommerceServiceImp;

/**
 * Main class for the E-commerce application.
 * 
 * Provides a command-line interface for users to interact with the application.
 * 
 *
 * @Date 20/10/24
 */

public class Main {
    public static void main(String[] args) {
    	IEcommerceService eser = new EcommerceServiceImp();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. register customer");
            System.out.println("2. create product");
            System.out.println("3. delete product");
            System.out.println("4. Add to cart");
            System.out.println("5. view cart");
            System.out.println("6. place order");
            System.out.println("7. view customer order");
            System.out.println("8. exit");
            System.out.println("Enter your choice");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    eser.createCustomer();
                    break;
                case 2:
                    eser.createProduct();
                    break;
                case 3:
                    try {
                        eser.deleteProduct();
                    } catch (ProductNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    eser.addToCart();
                    break;
                case 5:
                    eser.getAllFromCart();
                    break;
                case 6:
                    eser.placeOrder();
                    break;
                case 7:
                    try {
                        eser.getOrdersByCustomer();
                    } catch (CustomerNotFoundException e) {
                        e.printStackTrace();
                    } catch (OrderNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return; // Exits the loop and the program
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}