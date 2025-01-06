package readerspriority;

public class Main {
    public static void main(String[] args) {
        ReaderWriterReadersPriority rw = new ReaderWriterReadersPriority();

        // Readers
        Runnable readerTask = () -> {
            int readerId = Integer.parseInt(Thread.currentThread().getName());
            try {
                rw.read(readerId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // Writers
        Runnable writerTask = () -> {
            int writerId = Integer.parseInt(Thread.currentThread().getName());
            try {
                rw.write(writerId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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

