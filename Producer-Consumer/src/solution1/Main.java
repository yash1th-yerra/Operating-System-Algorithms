package solution1;

public class Main {
    public static void main(String[] args) {
        ProducerConsumerSemaphore pc = new ProducerConsumerSemaphore();
        new Thread(()->{
            try{
                for(int i=1;i<=10;i++){
                    pc.produce(i);      // produce
                    Thread.sleep(100);  //simulate production delay
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }).start();



        new Thread(()->{
            try{
                for(int i=1;i<=10;i++){
                    pc.consume();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
