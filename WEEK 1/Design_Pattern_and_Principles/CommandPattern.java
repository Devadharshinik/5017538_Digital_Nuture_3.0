import java.util.Scanner;

// Define the Command Interface
interface Command {
    void execute();
}

// Implement the Receiver Class
class Light {
    void turnOn() {
        System.out.println("The light is ON");
    }

    void turnOff() {
        System.out.println("The light is OFF");
    }
}

// Implement Concrete Commands
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Implement the Invoker Class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Test Class
public class CommandPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        RemoteControl remoteControl = new RemoteControl();

        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Turn ON the light");
            System.out.println("2. Turn OFF the light");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            if (choice == 3) {
                break;
            }

            switch (choice) {
                case 1:
                    remoteControl.setCommand(lightOnCommand);
                    remoteControl.pressButton();
                    break;
                case 2:
                    remoteControl.setCommand(lightOffCommand);
                    remoteControl.pressButton();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }
}
