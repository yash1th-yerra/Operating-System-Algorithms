import java.util.Iterator;
import java.util.LinkedHashSet;

public class MruPageReplacement {


    public static void mruReplace(int[] pages,int frameCount){
        LinkedHashSet<Integer> memory = new LinkedHashSet<>(frameCount);
        int pageFaults=0;
        System.out.println("Page Reference | Memory Content        | Page Fault?");
        for(int page:pages){
            if(memory.contains(page)){
                memory.remove(page);
                memory.add(page);
                System.out.printf("      %2d       | %-20s | No%n", page, memory);
                continue;
            }
            pageFaults++;
            if(memory.size()==frameCount){
                memory.remove(memory.size()-1);
            }

            memory.add(page);
            System.out.printf("      %2d       | %-20s | Yes%n", page, memory);
        }
        System.out.println("Total Page Faults: " + pageFaults);

    }


}
