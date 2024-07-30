import java.util.Scanner;

public class SingletonPattern {

    // Logger class implementing Singleton pattern
    public static class Logger {
        // Private static instance of Logger
        private static Logger instance;

        // Private constructor to prevent instantiation
        private Logger() {
            // Initialization code, if any
        }

        // Public static method to provide access to the instance
        public static Logger getInstance() {
            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        // Method to log messages
        public void log(String message) {
            System.out.println("Log: " + message);
        }
    }

    // Test class to verify Singleton implementation
    public static class SingletonTest {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Get the instance of Logger
            Logger logger = Logger.getInstance();

            // Prompt user for a message to log
            System.out.println("Enter a message to log:");
            String userMessage = scanner.nextLine();

            // Log the user's message
            logger.log(userMessage);

            // Demonstrate that only one instance is used
            Logger anotherLogger = Logger.getInstance();
            if (logger == anotherLogger) {
                System.out.println("Both logger and anotherLogger are the same instance.");
            } else {
                System.out.println("logger and anotherLogger are different instances.");
            }

            scanner.close();
        }
    }
}
