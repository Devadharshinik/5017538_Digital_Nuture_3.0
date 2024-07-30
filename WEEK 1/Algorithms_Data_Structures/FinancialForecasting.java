import java.util.Scanner;

public class FinancialForecasting {

    // Recursive method to calculate future value
    public static double calculateFutureValueRecursive(double initialValue, double growthRate, int years) {
        // Base case: If the number of years is 0, return the initial value
        if (years == 0) {
            return initialValue;
        }
        // Recursive case: Calculate future value for one year less and apply the growth rate
        return calculateFutureValueRecursive(initialValue, growthRate, years - 1) * (1 + growthRate);
    }

    // Iterative method to calculate future value
    public static double calculateFutureValueIterative(double initialValue, double growthRate, int years) {
        double futureValue = initialValue;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Get user input for initial value
        System.out.print("Enter the initial value (e.g., starting amount of money): ");
        double initialValue = scanner.nextDouble();

        // Get user input for annual growth rate
        System.out.print("Enter the annual growth rate (e.g., 0.05 for 5%): ");
        double growthRate = scanner.nextDouble();

        // Get user input for number of years
        System.out.print("Enter the number of years into the future: ");
        int years = scanner.nextInt();

        // Calculate future value using recursive approach
        double futureValueRecursive = calculateFutureValueRecursive(initialValue, growthRate, years);
        System.out.println("Future Value after " + years + " years (Recursive): " + futureValueRecursive);

        // Calculate future value using iterative approach
        double futureValueIterative = calculateFutureValueIterative(initialValue, growthRate, years);
        System.out.println("Future Value after " + years + " years (Iterative): " + futureValueIterative);

        // Close the scanner
        scanner.close();
    }
}
