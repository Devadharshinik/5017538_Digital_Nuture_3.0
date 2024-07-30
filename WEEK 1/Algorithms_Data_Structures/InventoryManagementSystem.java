import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagementSystem {

    // Product class definition
    public static class Product {
        private String productId;
        private String productName;
        private int quantity;
        private double price;

        // Constructor
        public Product(String productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        // Getters and Setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    // InventoryManagementSystem class definition
    private Map<String, Product> inventory;
    private Scanner scanner;

    // Constructor
    public InventoryManagementSystem() {
        inventory = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Add a product
    public void addProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Product product = new Product(id, name, quantity, price);
        inventory.put(id, product);
        System.out.println("Product added.");
    }

    // Update a product
    public void updateProduct() {
        System.out.print("Enter product ID to update: ");
        String id = scanner.nextLine();
        if (inventory.containsKey(id)) {
            System.out.print("Enter new product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Product product = new Product(id, name, quantity, price);
            inventory.put(id, product);
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product
    public void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        String id = scanner.nextLine();
        if (inventory.containsKey(id)) {
            inventory.remove(id);
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Retrieve a product
    public void getProduct() {
        System.out.print("Enter product ID to retrieve: ");
        String id = scanner.nextLine();
        Product product = inventory.get(id);
        if (product != null) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Price: " + product.getPrice());
        } else {
            System.out.println("Product not found.");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Get Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ims.addProduct();
                    break;
                case 2:
                    ims.updateProduct();
                    break;
                case 3:
                    ims.deleteProduct();
                    break;
                case 4:
                    ims.getProduct();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
