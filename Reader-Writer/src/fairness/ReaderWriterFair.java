package fairness;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterFair {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(int readerId) {
        lock.readLock().lock();
        try {
            System.out.println("Reader " + readerId + " is reading.");
            Thread.sleep(100); // Simulate reading
            System.out.println("Reader " + readerId + " has finished reading.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int writerId) {
        lock.writeLock().lock();
        try {
            System.out.println("Writer " + writerId + " is writing.");
            Thread.sleep(200); // Simulate writing
            System.out.println("Writer " + writerId + " has finished writing.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}