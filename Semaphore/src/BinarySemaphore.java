import java.util.concurrent.Semaphore;

public class BinarySemaphore {

    static Semaphore semaphore = new Semaphore(1);
    // 1 indicates binary semaphore

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> accessResource("Thread1"));
        Thread t2 = new Thread(()-> accessResource("Thread2"));
        t1.start();
        t2.start();
    }

    private static void accessResource(String threadName){
        try{
            System.out.println(threadName + " is trying to access critical section/ resource"); // Non critical section
            semaphore.acquire();    // wait() or up operation of semaphore  // entry section
            System.out.println(threadName+ " is in critical section"); // this is critical section
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {  // this is exit section
            System.out.println(threadName+" leaving critical section");
            semaphore.release(); // this is singal() or down() operation
        }
    }
}
