package fairness;

public class Main {
    public static void main(String[] args) {
        ReaderWriterFair rw = new ReaderWriterFair();

        // Readers
        Runnable readerTask = () -> {
            int readerId = Integer.parseInt(Thread.currentThread().getName());
            rw.read(readerId);
        };

        // Writers
        Runnable writerTask = () -> {
            int writerId = Integer.parseInt(Thread.currentThread().getName());
            rw.write(writerId);
        };

        // Threads
        for (int i = 1; i <= 3; i++) {
            new Thread(readerTask, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 2; i++) {
            new Thread(writerTask, String.valueOf(i)).start();
        }
    }
}
