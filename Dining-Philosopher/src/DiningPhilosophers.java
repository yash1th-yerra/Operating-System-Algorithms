import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];
    private static final Semaphore room = new Semaphore(NUM_PHILOSOPHERS - 1); // Allow only 4 philosophers to pick forks

    public static void main(String[] args) {
        Thread[] philosophers = new Thread[NUM_PHILOSOPHERS];

        // Initialize semaphores for forks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1); // Each fork is initially available
        }

        // Create philosopher threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            final int philosopherId = i; // Required to avoid lambda scope issues
            philosophers[i] = new Thread(() -> philosopher(philosopherId));
            philosophers[i].start();
        }

        // Wait for all threads to finish (not strictly necessary as the threads run indefinitely)
        for (Thread philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void philosopher(int id) {
        while (true) {
            think(id);

            try {
                // Enter the room (allow up to NUM_PHILOSOPHERS - 1 philosophers to pick forks)
                room.acquire();

                // Pick up left fork
                forks[id].acquire();

                // Pick up right fork
                forks[(id + 1) % NUM_PHILOSOPHERS].acquire();

                eat(id);

                // Put down right fork
                forks[(id + 1) % NUM_PHILOSOPHERS].release();

                // Put down left fork
                forks[id].release();

                // Leave the room
                room.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void think(int id) {
        System.out.println("Philosopher " + id + " is thinking.");
        try {
            Thread.sleep((int) (Math.random() * 1000)); // Simulate thinking time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void eat(int id) {
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep((int) (Math.random() * 1000)); // Simulate eating time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
