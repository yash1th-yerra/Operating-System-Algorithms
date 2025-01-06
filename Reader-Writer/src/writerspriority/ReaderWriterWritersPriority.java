package writerspriority;

import java.util.concurrent.Semaphore;

public class ReaderWriterWritersPriority {

    private int readerCount = 0;
    private final Semaphore mutex = new Semaphore(1); // Protects readerCount
    private final Semaphore writeLock = new Semaphore(1); // Ensures exclusive writer access
    private final Semaphore readerQueue = new Semaphore(1); // Blocks new readers during writer's wait

    public void read(int readerId) throws InterruptedException{
        readerQueue.acquire();  // block new readers if a writer is waiting
        mutex.acquire();
        readerCount++;
        if(readerCount==1) writeLock.acquire();     //first reader locks the writer
        mutex.release();
        readerQueue.release();
        //reading section

        System.out.println("Reader "+readerId+" is reading");
        Thread.sleep(200);  // simulate reading
        System.out.println("Reader "+readerId+" finished reading");

        mutex.acquire();
        readerCount--;
        if(readerCount==0) writeLock.release(); // last reader unlocks the writer
        mutex.release();

    }


    public void write(int writerId) throws InterruptedException {
        writeLock.acquire(); // Ensure exclusive writer access

        // Writing section
        System.out.println("Writer " + writerId + " is writing.");
        Thread.sleep(200); // Simulate writing
        System.out.println("Writer " + writerId + " has finished writing.");

        writeLock.release();
    }
}
