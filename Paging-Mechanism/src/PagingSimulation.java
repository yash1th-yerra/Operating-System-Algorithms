public class PagingSimulation {
    public static void main(String[] args) {
        int pageSize = 4;      // Each page is 4 KB
        int memorySize = 16;   // Total memory is 16 KB (4 frames)

        PagingSystem pagingSystem = new PagingSystem(pageSize, memorySize);

        // Simulate loading pages
        pagingSystem.loadPage(0);
        pagingSystem.loadPage(1);
        pagingSystem.loadPage(2);
        pagingSystem.loadPage(3);

        // Display page table and memory state
        pagingSystem.displayPageTable();
        pagingSystem.displayMemory();

        // Simulate address translation
        int logicalAddress = 5; // Logical address to translate
        int physicalAddress = pagingSystem.translateAddress(logicalAddress);
        System.out.println("Logical Address " + logicalAddress + " -> Physical Address " + physicalAddress);

        // Trigger page replacement
        pagingSystem.loadPage(4);
        pagingSystem.displayPageTable();
        pagingSystem.displayMemory();
    }
}