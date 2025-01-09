public class SegmentSimulation {
    public static void main(String[] args) {
        SegmentationSystem segmentationSystem = new SegmentationSystem();

        // Add segments (name, base address, size/limit)
        segmentationSystem.addSegment("Code", 0, 100);
        segmentationSystem.addSegment("Data", 100, 50);
        segmentationSystem.addSegment("Stack", 150, 30);

        // Display all segments
        segmentationSystem.displaySegments();

        // Translate logical addresses to physical addresses
        try {
            int physicalAddress1 = segmentationSystem.translateAddress("Code", 20);
            System.out.println("Logical Address 20 in Code Segment -> Physical Address: " + physicalAddress1);

            int physicalAddress2 = segmentationSystem.translateAddress("Data", 40);
            System.out.println("Logical Address 40 in Data Segment -> Physical Address: " + physicalAddress2);

            // This will throw an exception as the logical address is out of bounds
            int physicalAddress3 = segmentationSystem.translateAddress("Stack", 35);
            System.out.println("Logical Address 35 in Stack Segment -> Physical Address: " + physicalAddress3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}