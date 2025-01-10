import java.util.ArrayList;
import java.util.List;

public class OptimalPageReplacement {

    public static void optimalReplacement(int[] pages,int frameCount){
        List<Integer> memory = new ArrayList<>();
        int pageFaults= 0;
        System.out.println("Page Reference | Memory Content        | Page Fault?");
        for(int i=0;i<pages.length;i++){
            int page = pages[i];
            if(!memory.contains(page)){
                pageFaults++;
                if(memory.size()==frameCount){
                    int farthestIndex =-1;
                    int pageToReplace = -1;
                    for (int memPage : memory) {
                        int nextUse = findNextUse(pages, i + 1, memPage);
                        if (nextUse > farthestIndex) {
                            farthestIndex = nextUse;
                            pageToReplace = memPage;
                        }
                    }
                    memory.remove(Integer.valueOf(pageToReplace));

                }
                memory.add(page);

                System.out.printf("      %2d       | %-20s | Yes%n", page, memory);
            } else {
                // Page Hit (no fault)
                System.out.printf("      %2d       | %-20s | No%n", page, memory);
            }
        }

        // Print total page faults
        System.out.println("Total Page Faults: " + pageFaults);
    }
    private static int findNextUse(int[] pages, int start, int page) {
        for (int i = start; i < pages.length; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return Integer.MAX_VALUE; // Page is not used again
    }
}
