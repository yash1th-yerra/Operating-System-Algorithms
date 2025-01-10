





public class Main {
    public static void main(String[] args) {
        IndexedFileAllocation ifa = new IndexedFileAllocation(10);

        ifa.allocateFile("FileA", 3);
        ifa.allocateFile("FileB", 4);
        ifa.displayDisk();
        ifa.displayFileTable();
    }
}
