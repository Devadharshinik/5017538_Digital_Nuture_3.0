import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Define the Subject Interface
interface Image {
    void display();
}

// Real Subject Class
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromServer();
    }

    private void loadImageFromServer() {
        System.out.println("Loading image: " + filename + " from remote server...");
        // Simulate loading time
        try {
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Image loaded: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy Class
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    private static Map<String, RealImage> cache = new HashMap<>();

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = cache.get(filename);
            if (realImage == null) {
                realImage = new RealImage(filename);
                cache.put(filename, realImage);
            }
        }
        realImage.display();
    }
}

// Test Class
public class ProxyPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to select an image
        System.out.println("Enter the filename of the image to display:");
        String filename = scanner.nextLine();

        // Create a proxy for the image
        Image image = new ProxyImage(filename);

        // Display the image
        image.display();

        // Display the image again to see caching in action
        System.out.println("Displaying the same image again:");
        image.display();

        scanner.close();
    }
}
