import java.util.Scanner;

// Define the component interface
interface Notifier {
    void send(String message);
}

// Concrete component
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// Abstract decorator class
abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
    }
}

// Concrete decorator classes
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Slack message: " + message);
    }
}

// Test class to demonstrate decorator pattern
public class DecoratorPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the base email notifier
        Notifier emailNotifier = new EmailNotifier();

        // Prompt user for additional decorators
        System.out.println("Do you want to add SMS notification? (yes/no):");
        boolean addSMS = scanner.nextLine().trim().equalsIgnoreCase("yes");

        System.out.println("Do you want to add Slack notification? (yes/no):");
        boolean addSlack = scanner.nextLine().trim().equalsIgnoreCase("yes");

        // Apply decorators based on user input
        if (addSMS) {
            emailNotifier = new SMSNotifierDecorator(emailNotifier);
        }
        if (addSlack) {
            emailNotifier = new SlackNotifierDecorator(emailNotifier);
        }

        // Prompt user for the notification message
        System.out.println("Enter the message to send:");
        String message = scanner.nextLine();

        // Send the notification
        emailNotifier.send(message);

        scanner.close();
    }
}
