import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LookDishScheduling {
    public static void look(int[] requests, int initialHead, String direction) {
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

        // Determine execution order based on direction
        if (direction.equalsIgnoreCase("up")) {
            // Service right, then left
            executionOrder.addAll(right);
            Collections.reverse(left);
            executionOrder.addAll(left);
        } else {
            // Service left, then right
            Collections.reverse(left);
            executionOrder.addAll(left);
            executionOrder.addAll(right);
        }

        System.out.println("Execution Order:");
        for (int request : executionOrder) {
            System.out.println("Move from " + currentHead + " to " + request);
            totalHeadMovement += Math.abs(currentHead - request);
            currentHead = request;
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement + " cylinders.");
    }


}
