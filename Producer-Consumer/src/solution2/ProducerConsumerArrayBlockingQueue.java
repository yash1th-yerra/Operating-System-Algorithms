package solution2;

import javax.print.DocFlavor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerArrayBlockingQueue {
    private static final int BUFFER_SIZE=5;
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);


    public void produce(int item ) throws InterruptedException{
        buffer.put(item);       // automatically handles wait if buffer is full
        System.out.println("Produced: "+item);

    }

    public int consume() throws InterruptedException{
        int item = buffer.take(); // automatically handles wait if buffer is empty
        System.out.println("Consumed: "+item);
        return item;
    }

}
