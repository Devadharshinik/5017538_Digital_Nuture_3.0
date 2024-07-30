import java.util.Scanner;

public class BuilderPattern {

    // Product class
    public static class Computer {
        private final String CPU;
        private final int RAM;
        private final int storage;
        private final boolean hasGraphicsCard;
        private final boolean hasWiFi;
        private final boolean hasBluetooth;

        // Private constructor to be used by the Builder
        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.hasGraphicsCard = builder.hasGraphicsCard;
            this.hasWiFi = builder.hasWiFi;
            this.hasBluetooth = builder.hasBluetooth;
        }

        @Override
        public String toString() {
            return "Computer [CPU=" + CPU + ", RAM=" + RAM + "GB, Storage=" + storage + "GB, GraphicsCard=" + (hasGraphicsCard ? "Yes" : "No") +
                    ", WiFi=" + (hasWiFi ? "Yes" : "No") + ", Bluetooth=" + (hasBluetooth ? "Yes" : "No") + "]";
        }

        // Static nested Builder class
        public static class Builder {
            private String CPU;
            private int RAM;
            private int storage;
            private boolean hasGraphicsCard;
            private boolean hasWiFi;
            private boolean hasBluetooth;

            // Setters for each attribute
            public Builder setCPU(String CPU) {
                this.CPU = CPU;
                return this;
            }

            public Builder setRAM(int RAM) {
                this.RAM = RAM;
                return this;
            }

            public Builder setStorage(int storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGraphicsCard(boolean hasGraphicsCard) {
                this.hasGraphicsCard = hasGraphicsCard;
                return this;
            }

            public Builder setWiFi(boolean hasWiFi) {
                this.hasWiFi = hasWiFi;
                return this;
            }

            public Builder setBluetooth(boolean hasBluetooth) {
                this.hasBluetooth = hasBluetooth;
                return this;
            }

            // Build method to create Computer instance
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // Test class
    public static class BuilderPatternTest {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Get user input for Computer configuration
            System.out.println("Enter CPU (e.g., Intel Core i7):");
            String cpu = scanner.nextLine();

            System.out.println("Enter RAM in GB (e.g., 16):");
            int ram = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter Storage in GB (e.g., 512):");
            int storage = Integer.parseInt(scanner.nextLine());

            System.out.println("Do you want a graphics card? (yes/no):");
            boolean hasGraphicsCard = scanner.nextLine().equalsIgnoreCase("yes");

            System.out.println("Do you want WiFi? (yes/no):");
            boolean hasWiFi = scanner.nextLine().equalsIgnoreCase("yes");

            System.out.println("Do you want Bluetooth? (yes/no):");
            boolean hasBluetooth = scanner.nextLine().equalsIgnoreCase("yes");

            // Build the Computer object with user input
            Computer userComputer = new Computer.Builder()
                    .setCPU(cpu)
                    .setRAM(ram)
                    .setStorage(storage)
                    .setGraphicsCard(hasGraphicsCard)
                    .setWiFi(hasWiFi)
                    .setBluetooth(hasBluetooth)
                    .build();

            // Print the configured Computer
            System.out.println("Your configured computer:");
            System.out.println(userComputer);

            scanner.close();
        }
    }
}
