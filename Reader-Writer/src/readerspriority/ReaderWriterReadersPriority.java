package readerspriority;

import java.util.concurrent.Semaphore;

public class ReaderWriterReadersPriority {
    private int readerCount = 0;
    private final Semaphore mutex= new Semaphore(1);    // protects readercount
    private final Semaphore writeLock = new Semaphore(1);   //allows one writer at any time

    public void read(int readerId) throws InterruptedException{
        mutex.acquire();    // protects access to readCount
        readerCount++;
        if(readerCount==1) writeLock.acquire();     // first reader locks the writer
        mutex.release();
        System.out.println("Reader "+readerId+" is reading.");
        Thread.sleep(100);  // simulating reading process
        System.out.println("Reader "+readerId+" is finished reading");

        mutex.acquire();    // protects access to readercount
        readerCount--;
        if(readerCount==0) writeLock.release();     // last reader unlocks writer
        mutex.release();
    }


    // w1 came in and acquired lock on db
    // w2 came in at same time

    public void write(int writerId) throws InterruptedException{
        writeLock.acquire();
        System.out.println("Writer "+writerId+" is writing");   // critical section
        Thread.sleep(200);  // simulating reading process
        System.out.println("Writer "+writerId+" finished writing");

        writeLock.release();    // signalling reader/writer to acquire lock on db


    }
}
