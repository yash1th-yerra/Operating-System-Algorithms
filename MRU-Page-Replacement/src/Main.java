public class Main {
    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
        int frameCount = 3;

        // Run the MRU Page Replacement Algorithm
        MruPageReplacement mruPageReplacement = new MruPageReplacement();
        mruPageReplacement.mruReplace(pages, frameCount);
    }
}