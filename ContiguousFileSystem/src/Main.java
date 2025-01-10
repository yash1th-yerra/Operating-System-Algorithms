public class Main {
    public static void main(String[] args) {
        ContiguousAllocation ca = new ContiguousAllocation(20);

        ca.allocateFile("FileA", 5);
        ca.allocateFile("FileB", 7);
        ca.allocateFile("FileC", 4);
        ca.displayFileTable();
    }
}