import java.util.Arrays;

public class RealCaseBankersAlgorithm {
    private int numProcesses;  // Number of processes
    private int numResources;  // Number of resources
    private int[] available;   // Available resources
    private int[][] allocation; // Allocated resources for each process
    private int[][] max;        // Maximum resources required by each process
    private int[][] need;       // Resources still needed by each process

    public RealCaseBankersAlgorithm(int numProcesses, int numResources, int[] available, int[][] max) {
        this.numProcesses = numProcesses;
        this.numResources = numResources;
        this.available = available;
        this.max = max;
        this.allocation = new int[numProcesses][numResources];
        this.need = new int[numProcesses][numResources];

        // Initialize need matrix (max - allocation)
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    // Function to request resources for a process
    public boolean requestResources(int processId, int[] request) {
        // Check if request is less than or equal to need
        for (int i = 0; i < numResources; i++) {
            if (request[i] > need[processId][i]) {
                System.out.println("Error: Process has exceeded maximum claim.");
                return false;
            }
        }

        // Check if request is less than or equal to available resources
        for (int i = 0; i < numResources; i++) {
            if (request[i] > available[i]) {
                System.out.println("Resources are not available.");
                return false;
            }
        }

        // Temporarily allocate resources
        for (int i = 0; i < numResources; i++) {
            available[i] -= request[i];
            allocation[processId][i] += request[i];
            need[processId][i] -= request[i];
        }

        // Check if the system remains in a safe state
        if (isSafe()) {
            System.out.println("Request granted.");
            return true;
        } else {
            // Rollback if not safe
            for (int i = 0; i < numResources; i++) {
                available[i] += request[i];
                allocation[processId][i] -= request[i];
                need[processId][i] += request[i];
            }
            System.out.println("Request denied: System would be in an unsafe state.");
            return false;
        }
    }

    // Function to release resources for a process
    public void releaseResources(int processId, int[] release) {
        for (int i = 0; i < numResources; i++) {
            allocation[processId][i] -= release[i];
            available[i] += release[i];
            need[processId][i] += release[i];
        }
        System.out.println("Resources released by Process " + processId);
    }

    // Banker's Algorithm: Check if the system is in a safe state
    public boolean isSafe() {
        boolean[] finish = new boolean[numProcesses];
        int[] work = Arrays.copyOf(available, numResources);
        int[] safeSequence = new int[numProcesses];
        int count = 0;

        while (count < numProcesses) {
            boolean found = false;

            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i]) {
                    boolean canAllocate = true;

                    for (int j = 0; j < numResources; j++) {
                        if (need[i][j] > work[j]) {
                            canAllocate = false;
                            break;
                        }
                    }

                    if (canAllocate) {
                        for (int j = 0; j < numResources; j++) {
                            work[j] += allocation[i][j];
                        }
                        finish[i] = true;
                        safeSequence[count++] = i;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is in an unsafe state.");
                return false;
            }
        }

        System.out.println("System is in a safe state.");
        System.out.print("Safe Sequence: ");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        // Number of processes and resources
        int numProcesses = 5;
        int numResources = 3;

        // Available resources
        int[] available = {3, 3, 2};

        // Maximum resources required by each process
        int[][] max = {
                {7, 5, 3},
                {3, 2, 2},
                {9, 0, 2},
                {2, 2, 2},
                {4, 3, 3}
        };

        RealCaseBankersAlgorithm ba = new RealCaseBankersAlgorithm(numProcesses, numResources, available, max);

        // Request resources for Process 1
        int[] request1 = {1, 0, 2}; // Process 1 requests 1 unit of Resource 0, 0 units of Resource 1, and 2 units of Resource 2
        ba.requestResources(1, request1);

        // Request resources for Process 3
        int[] request2 = {3, 3, 0}; // Process 3 requests 3 units of Resource 0, 3 units of Resource 1, and 0 units of Resource 2
        ba.requestResources(3, request2);

        // Release resources from Process 0
        int[] release = {2, 1, 1}; // Process 0 releases 2 units of Resource 0, 1 unit of Resource 1, and 1 unit of Resource 2
        ba.releaseResources(0, release);
    }
}
