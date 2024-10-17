package Task6;

import java.util.ArrayList;
import java.util.List;

import Task1.Product;

public class ProductManager {
    private List<Product> products = new ArrayList<>();

    // Method to add a product
    public void addProduct(Product product) throws Exception {
        for (Product p : products) {
            if (p.getProductID() == product.getProductID()) {
                throw new Exception("Duplicate product with ID: " + product.getProductID());
            }
        }
        products.add(product);
    }

    // Method to update a product
    public void updateProduct(int productID, Product updatedProduct) throws Exception {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == productID) {
                products.set(i, updatedProduct);
                return;
            }
        }
        throw new Exception("Product not found for ID: " + productID);
    }

    // Method to remove a product
    public void removeProduct(int productID) throws Exception {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == productID) {
                products.remove(i);
                return;
            }
        }
        throw new Exception("Product not found for ID: " + productID);
    }

}
