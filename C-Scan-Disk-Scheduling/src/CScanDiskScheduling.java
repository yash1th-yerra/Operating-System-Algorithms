import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CScanDiskScheduling {
    public static void cScan(int[] requests, int initialHead, int diskSize) {
        int totalHeadMovement = 0;
        int currentHead = initialHead;

        // Sort the requests in ascending order
        Arrays.sort(requests);

        List<Integer> executionOrder = new ArrayList<>();

        // Separate requests into two lists based on head position
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int request : requests) {
            if (request < initialHead) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        // Service requests on the right side
        executionOrder.addAll(right);

        // Jump to the start (0) and service requests on the left side
        executionOrder.add(0); // Representing the jump
        executionOrder.addAll(left);

        System.out.println("Execution Order:");
        for (int i = 0; i < executionOrder.size(); i++) {
            int nextRequest = executionOrder.get(i);
            System.out.println("Move from " + currentHead + " to " + nextRequest);
            totalHeadMovement += Math.abs(currentHead - nextRequest);
            currentHead = nextRequest;
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement + " cylinders.");
    }


}
