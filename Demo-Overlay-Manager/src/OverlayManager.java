import java.util.Scanner;

class OverlayManager {
    // Base function, always in memory
    public void functionA() {
        System.out.println("Function A (Base) is always in memory.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to load Function B, or 2 to load Function C:");
        int choice = scanner.nextInt();

        // Load the required overlay
        if (choice == 1) {
            loadFunctionB();
        } else if (choice == 2) {
            loadFunctionC();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
    }

    // Overlay 1: Function B
    private void loadFunctionB() {
        System.out.println("Loading Function B (Overlay 1)...");
        functionB();
        System.out.println("Unloading Function B...");
    }

    private void functionB() {
        System.out.println("Function B is executing.");
    }

    // Overlay 2: Function C
    private void loadFunctionC() {
        System.out.println("Loading Function C (Overlay 2)...");
        functionC();
        System.out.println("Unloading Function C...");
    }

    private void functionC() {
        System.out.println("Function C is executing.");
    }
}

