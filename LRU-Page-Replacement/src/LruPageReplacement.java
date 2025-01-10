import jdk.dynalink.DynamicLinkerFactory;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class LruPageReplacement {


    public static void lruReplace(int[] pages,int frameCount){
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
                Iterator<Integer> iterator = memory.iterator();
                int lruPage = iterator.next();
                memory.remove(lruPage);
            }

            memory.add(page);
            System.out.printf("      %2d       | %-20s | Yes%n", page, memory);
        }
        System.out.println("Total Page Faults: " + pageFaults);

    }


}
