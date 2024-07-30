import java.util.Scanner;

// Repository Interface
interface CustomerRepository {
    String findCustomerById(int id);
}

// Concrete Repository Implementation
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // In a real application, this would retrieve data from a database
        // Here, we'll simulate with a simple switch case
        switch (id) {
            case 1:
                return "Alice Johnson";
            case 2:
                return "Bob Smith";
            case 3:
                return "Charlie Brown";
            default:
                return "Customer not found";
        }
    }
}

// Service Class
class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getCustomerName(int id) {
        return customerRepository.findCustomerById(id);
    }
}

// Main Class to Test Dependency Injection
public class DependencyInjection {
    public static void main(String[] args) {
        // Create a repository instance
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject repository into the service
        CustomerService service = new CustomerService(repository);

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer ID to find: ");
        int customerId = scanner.nextInt();

        // Fetch and display customer details
        String customerName = service.getCustomerName(customerId);
        System.out.println("Customer Name: " + customerName);

        scanner.close();
    }
}