package solution3;

public class Main {
    public static void main(String[] args) {
        ProducerConsumerWaitNotify pc= new ProducerConsumerWaitNotify();
        // Producer thread
        new Thread(() -> {
            try {

                for (int i = 1; i <= 10; i++) {
                    pc.produce(i);
                    Thread.sleep(100); // Simulate production delay
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    pc.consume();
                    Thread.sleep(200); // Simulate consumption delay
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
        }).start();


    }
}
