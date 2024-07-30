import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define the Subject Interface
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// Define the Observer Interface
interface Observer {
    void update(String stockName, double newPrice);
}

// Implement the Concrete Subject
class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double stockPrice;

    public StockMarket(String stockName) {
        this.stockName = stockName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }
}

// Implement Concrete Observers
class MobileApp implements Observer {
    @Override
    public void update(String stockName, double newPrice) {
        System.out.println("Mobile App Notification: " + stockName + " price updated to $" + newPrice);
    }
}

class WebApp implements Observer {
    @Override
    public void update(String stockName, double newPrice) {
        System.out.println("Web App Notification: " + stockName + " price updated to $" + newPrice);
    }
}

// Test Class
public class ObserverPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a StockMarket instance
        StockMarket stockMarket = new StockMarket("ABC Corp");

        // Create observers
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        // Register observers
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Prompt user to update stock price
        while (true) {
            System.out.println("Enter new stock price for " + stockMarket.getStockName() + " (or 'exit' to quit):");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                double newPrice = Double.parseDouble(input);
                stockMarket.setStockPrice(newPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        scanner.close();
    }
}
