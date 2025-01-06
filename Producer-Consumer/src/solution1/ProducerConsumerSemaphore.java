package solution1;

import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphore {

    private static final int BUFFER_SIZE = 5;
    private final int[] buffer  = new int[BUFFER_SIZE];
    private  int in= 0,out=0;


    private final Semaphore empty = new Semaphore(BUFFER_SIZE);     // track empty slots
    private final Semaphore full = new Semaphore(0);        // track filled slots
    private final Semaphore mutex = new Semaphore(1);       // ensures mutual exclusion


    public void produce(int item) throws InterruptedException {
        empty.acquire();        // wait if buffer is full
        mutex.acquire();        // enter into critical section

        buffer[in] = item;
        System.out.println("Produced: "+item);
        in = (in+1) % BUFFER_SIZE;

        mutex.release();        // exit critical section
        full.release();        // signal other threads that buffer is avaiable
    }

    public void consume() throws InterruptedException {
        full.acquire();         // wait if buffer is empty
        mutex.acquire();        // enter into critical section

        int item = buffer[out];
        System.out.println("Consumes: "+item);
        out = (out+1)%BUFFER_SIZE;

        mutex.release();        // exit critical section
        empty.release();         // signal that buffer is available
    }
}
