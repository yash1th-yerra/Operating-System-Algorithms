import java.util.ArrayList;
import java.util.List;

public class SstfDiskScheduling {
    public static void sstf(int[] requests, int initialHead) {
        int totalHeadMovement = 0;
        int currentHead = initialHead;

        // Create a list of unprocessed requests
        List<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        System.out.println("Execution Order:");

        while (!requestList.isEmpty()) {
            // Find the closest request to the current head
            int closestRequest = requestList.get(0);
            int minDistance = Math.abs(currentHead - closestRequest);

            for (int request : requestList) {
                int distance = Math.abs(currentHead - request);
                if (distance < minDistance) {
                    closestRequest = request;
                    minDistance = distance;
                }
            }

            // Move to the closest request
            System.out.println("Move from " + currentHead + " to " + closestRequest);
            totalHeadMovement += minDistance;
            currentHead = closestRequest;

            // Remove the processed request
            requestList.remove(Integer.valueOf(closestRequest));
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement + " cylinders.");
    }

}
