import java.util.concurrent.Semaphore;

public class CountingSemaphore {

    static Semaphore semaphore = new Semaphore(3);  // pool of resources i.e.3

    public static void main(String[] args) {
        for(int i=1;i<=6;i++){  // pool of theads i.e. 6 threads
            new Thread(new Worker("Thread "+i)).start();
        }
    }
    static class Worker implements Runnable{
        String name;
        Worker(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try{
                System.out.println(name+" is trying to acquire the resource");
                semaphore.acquire();    // entry section // wait() operation
                System.out.println(name+" aquired resource");
                Thread.sleep(2000);    // critical section
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }finally{
                System.out.println(name+" is releasing resource");
                semaphore.release();    // signal() operation // exit point
            }
        }
    }
}
