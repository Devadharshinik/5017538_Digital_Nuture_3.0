import java.util.Scanner;

// Define the target interface
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee classes representing different payment gateways
class PayPalPaymentGateway {
    void processPayPalPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class StripePaymentGateway {
    void processStripePayment(double amount) {
        System.out.println("Processing Stripe payment of $" + amount);
    }
}

// Adapter class for PayPal
class PayPalAdapter implements PaymentProcessor {
    private PayPalPaymentGateway payPalPaymentGateway;

    public PayPalAdapter(PayPalPaymentGateway payPalPaymentGateway) {
        this.payPalPaymentGateway = payPalPaymentGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalPaymentGateway.processPayPalPayment(amount);
    }
}

// Adapter class for Stripe
class StripeAdapter implements PaymentProcessor {
    private StripePaymentGateway stripePaymentGateway;

    public StripeAdapter(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    @Override
    public void processPayment(double amount) {
        stripePaymentGateway.processStripePayment(amount);
    }
}

// Test class to demonstrate the use of different payment gateways through the adapter
public class AdapterPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for payment gateway choice
        System.out.println("Enter the payment gateway to use (PayPal, Stripe):");
        String gatewayChoice = scanner.nextLine().trim().toLowerCase();

        // Create the appropriate PaymentProcessor based on user input
        PaymentProcessor paymentProcessor = null;

        switch (gatewayChoice) {
            case "paypal":
                paymentProcessor = new PayPalAdapter(new PayPalPaymentGateway());
                break;
            case "stripe":
                paymentProcessor = new StripeAdapter(new StripePaymentGateway());
                break;
            default:
                System.out.println("Invalid payment gateway choice.");
                System.exit(1);
        }

        // Prompt user for the payment amount
        System.out.println("Enter the amount to process:");
        double amount = scanner.nextDouble();

        // Process the payment using the chosen gateway
        paymentProcessor.processPayment(amount);

        scanner.close();
    }
}

