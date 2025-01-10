public class FcfsDiskScheduling {
    public static void fcfs(int[] requests, int initialHead) {
        int totalHeadMovement = 0;
        int currentHead = initialHead;

        System.out.println("Execution Order:");
        for (int request : requests) {
            System.out.println("Move from " + currentHead + " to " + request);
            totalHeadMovement += Math.abs(currentHead - request);
            currentHead = request;
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement + " cylinders.");
    }
}
