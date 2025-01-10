public class Main {
    public static void main(String[] args) {
        // Input: Reference string and number of frames
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4};
        int frameCount = 3;

        // Run the FIFO Page Replacement Algorithm
        FifoPageReplacement fifoPageReplacement = new FifoPageReplacement();
        fifoPageReplacement.fifoPageReplacement(pages, frameCount);
    }
}