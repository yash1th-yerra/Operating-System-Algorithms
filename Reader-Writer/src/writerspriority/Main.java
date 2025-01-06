package writerspriority;

public class Main {
    public static void main(String[] args) {
        ReaderWriterWritersPriority rw = new ReaderWriterWritersPriority();

        // Readers
        Runnable readerTask = () -> {
            int readerId = Integer.parseInt(Thread.currentThread().getName());
            try {
                rw.read(readerId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Writers
        Runnable writerTask = () -> {
            int writerId = Integer.parseInt(Thread.currentThread().getName());
            try {
                rw.write(writerId);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
