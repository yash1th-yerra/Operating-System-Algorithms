import java.util.*;

public class PagingSystem {

    private int pageSize;   // size of page
    private int memorySize; // size of total physical memory
    private int numFrame;   // total no of frames in physical memory
    private Map<Integer,Page> pageTable;       // maps pages to frame
    private List<Integer> physicalMemory;

    public PagingSystem(int pageSize,int memorySize){
        this.memorySize = memorySize;
        this.pageSize = pageSize;
        this.numFrame = memorySize/pageSize;
        this.pageTable = new HashMap<>();
        this.physicalMemory = new ArrayList<>(Collections.nCopies(numFrame,-1));
    }

    // load a page into memory

    public void loadPage(int pageNumber){
        if(pageTable.containsKey(pageNumber)){
            System.out.println("Page "+pageNumber+" is already exists in memory");
            return ;

        }


        int freeFrame = findFreeFrame();
        if(freeFrame==-1){
            System.out.println(
                    "Memory Full! Replacing a page... "
            );
        }
        else{
            pageTable.put(pageNumber,new Page(pageNumber,freeFrame));
            physicalMemory.set(freeFrame,pageNumber);
            System.out.println("Loaded page "+pageNumber+" into frame "+freeFrame);
        }
    }
    // translates logical address into physical address
    public int translateAddress(int logicalAddress){
        int pageNumber = logicalAddress/pageSize;
        int offset = logicalAddress%pageSize;


        if(!pageTable.containsKey(pageNumber)){
            System.out.println("Page Fault!! Loading Page "+pageNumber+"....");
            loadPage(pageNumber);
        }
        int frameNumber = pageTable.get(pageNumber).getFrameNumber();
        return (frameNumber*pageSize)+offset;

    }


    // Displays the current state of the page table
    public void displayPageTable() {
        System.out.println("Page Table:");
        for (Page page : pageTable.values()) {
            System.out.println(page);
        }
    }

    // Displays the current state of physical memory
    public void displayMemory() {
        System.out.println("Physical Memory (Frames):");
        for (int i = 0; i < physicalMemory.size(); i++) {
            System.out.println("Frame " + i + ": " + (physicalMemory.get(i) == -1 ? "Empty" : "Page " + physicalMemory.get(i)));
        }
    }


    // finds the first free frame in memory

    private int findFreeFrame(){
        for(int i=0;i<physicalMemory.size();i++){
            if(physicalMemory.get(i)==-1) return i;
        }
        return -1;
    }

    // replaces a page using simple FIFO algorithm policy

    private void replacePage(int newPageNumber){
        int frameToReplace = physicalMemory.get(0);
        pageTable.remove(frameToReplace);
        physicalMemory.remove(0);
        pageTable.put(newPageNumber,new Page(newPageNumber,physicalMemory.size()-1));
        physicalMemory.add(newPageNumber);
        System.out.println("Replaced Memory "+frameToReplace+" with Page"+newPageNumber);
    }

}
