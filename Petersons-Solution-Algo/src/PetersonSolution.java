public class PetersonSolution {
    private static class SharedResource {
        private static volatile boolean[] flag = new boolean[2];
        private static volatile int turn = 0;
        private int data = 0;

        public void increment() {
            data++;
        }

        public int getData() {
            return data;
        }
    }

    private static class Process implements Runnable {
        private int processId;
        private SharedResource resource;

        public Process(int processId, SharedResource resource) {
            this.processId = processId;
            this.resource = resource;
        }

        @Override
        public void run() {
            // Entry Section
            SharedResource.flag[processId] = true;
            SharedResource.turn = 1 - processId;

            // Busy Wait - Ensure mutual exclusion
            while (SharedResource.flag[1 - processId] &&
                    SharedResource.turn == (1 - processId)) {
                // Wait
            }

            // Critical Section
            try {
                resource.increment();
                System.out.println("Process " + processId +
                        " incremented: " + resource.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Exit Section
            SharedResource.flag[processId] = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();

        Thread p0 = new Thread(new Process(0, resource));
        Thread p1 = new Thread(new Process(1, resource));

        p0.start();
        p1.start();

        p0.join();
        p1.join();

        System.out.println("Final Value: " + resource.getData());
    }
}
