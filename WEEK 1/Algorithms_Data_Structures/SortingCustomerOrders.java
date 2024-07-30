import java.util.ArrayList;
import java.util.Collections;
// import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Define the Order class with attributes orderId, customerName, and totalPrice
class Order {
    int orderId;
    String customerName;
    double totalPrice;

    // Constructor
    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

public class SortingCustomerOrders {

    // Bubble Sort algorithm to sort orders by totalPrice
    public static void bubbleSort(List<Order> orders) {
        int n = orders.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders.get(j).getTotalPrice() > orders.get(j + 1).getTotalPrice()) {
                    // Swap orders[j] and orders[j+1]
                    Collections.swap(orders, j, j + 1);
                }
            }
        }
    }

    // Quick Sort algorithm to sort orders by totalPrice
    public static void quickSort(List<Order> orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(List<Order> orders, int low, int high) {
        double pivot = orders.get(high).getTotalPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders.get(j).getTotalPrice() <= pivot) {
                i++;
                Collections.swap(orders, i, j);
            }
        }
        Collections.swap(orders, i + 1, high);
        return i + 1;
    }

    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Get number of orders
        System.out.print("Enter the number of orders: ");
        int numOrders = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Initialize list of orders
        List<Order> orders = new ArrayList<>();

        // Get user input for each order
        for (int i = 0; i < numOrders; i++) {
            System.out.print("Enter order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter total price: ");
            double totalPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            orders.add(new Order(orderId, customerName, totalPrice));
        }

        // Display original list
        System.out.println("\nOriginal List:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        List<Order> bubbleSortedOrders = new ArrayList<>(orders);
        bubbleSort(bubbleSortedOrders);
        System.out.println("\nBubble Sorted List:");
        for (Order order : bubbleSortedOrders) {
            System.out.println(order);
        }

        // Quick Sort
        List<Order> quickSortedOrders = new ArrayList<>(orders);
        quickSort(quickSortedOrders, 0, quickSortedOrders.size() - 1);
        System.out.println("\nQuick Sorted List:");
        for (Order order : quickSortedOrders) {
            System.out.println(order);
        }

        // Close the scanner
        scanner.close();
    }
}

