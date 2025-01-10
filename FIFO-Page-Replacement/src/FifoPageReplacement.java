import java.util.LinkedList;
import java.util.Queue;

public class FifoPageReplacement {

    // @param pages -> array of page references
    // @param frameCount -> number frames available in memory
    public static void fifoPageReplacement(int[] pages,int frameCount){
        Queue<Integer> memory = new LinkedList<>(); // represents the memory frames
        int pageFaults =0;  // counter for pages faults

        System.out.println("Page Reference | Memory Count       | Page Fault?"  );
        for(int page: pages){
            if(!memory.contains(page)){
                pageFaults++;
                if(memory.size()==frameCount) memory.poll();
                memory.add(page);
                System.out.printf("      %2d       | %-20s | Yes%n", page, memory);
            }
            else{
                System.out.println("Page hit");
                System.out.printf("      %2d       | %-20s | Yes%n", page, memory);
            }
        }

        // total page faults
        System.out.println("Total page faults: "+pageFaults);


    }
}
