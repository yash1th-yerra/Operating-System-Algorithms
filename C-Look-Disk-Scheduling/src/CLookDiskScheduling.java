import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CLookDiskScheduling {
    public static void cLook(int[] requests, int initialHead) {
        int totalHeadMovement = 0;
        int currentHead = initialHead;

        // Sort the requests in ascending order
        Arrays.sort(requests);

        List<Integer> executionOrder = new ArrayList<>();

        // Separate requests into two groups based on head position
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

        // Jump to the first request on the left side
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
