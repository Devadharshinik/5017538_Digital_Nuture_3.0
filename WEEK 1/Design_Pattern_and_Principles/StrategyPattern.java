import java.util.Scanner;

// Define the Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Implement Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using Credit Card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using PayPal account: " + email);
    }
}

// Implement the Context Class
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}

// Test Class
public class StrategyPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentContext paymentContext = new PaymentContext();

        while (true) {
            System.out.println("Select Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                break;
            }

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Credit Card Number: ");
                    String cardNumber = scanner.nextLine();
                    paymentContext.setPaymentStrategy(new CreditCardPayment(cardNumber));
                    break;
                case 2:
                    System.out.print("Enter PayPal Email: ");
                    String email = scanner.nextLine();
                    paymentContext.setPaymentStrategy(new PayPalPayment(email));
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            paymentContext.executePayment(amount);
        }

        scanner.close();
    }
}
