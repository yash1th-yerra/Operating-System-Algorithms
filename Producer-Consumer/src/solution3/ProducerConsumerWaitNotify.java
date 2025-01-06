package solution3;

public class ProducerConsumerWaitNotify {

    private static final int BUFFER_SIZE = 5;
    private final int[] buffer = new int[BUFFER_SIZE];
    private int in = 0, out = 0, count = 0;


    public synchronized void produce(int item) throws InterruptedException{
        while(count==BUFFER_SIZE){
            wait();
        }
        buffer[in] = item;
        System.out.println("Produced: "+item);
        in = (in+1)%BUFFER_SIZE;
        count++;
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException{
        while(count==0){
            wait();
        }

        int item = buffer[out];
        System.out.println("Consumed: "+item);
        out = (out+1)%BUFFER_SIZE;
        count--;
        notifyAll();
        return item;

    }


}


