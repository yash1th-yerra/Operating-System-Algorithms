public class Main {
    public static void main(String[] args) {
        LinkedListAllocation lla = new LinkedListAllocation(10);

        lla.allocateFile("FileA", 3);
        lla.allocateFile("FileB", 4);
        lla.displayDisk();
        lla.displayFileTable();
    }
}