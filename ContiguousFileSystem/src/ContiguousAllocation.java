import java.util.ArrayList;
import java.util.List;

public class ContiguousAllocation {
    private final int totalBlocks;
    private final boolean[] disk;
    private final List<File> fileTable;

    public ContiguousAllocation(int totalBlocks) {
        this.totalBlocks = totalBlocks;
        this.disk = new boolean[totalBlocks];
        this.fileTable = new ArrayList<>();
    }

    public boolean allocateFile(String fileName, int fileSize) {
        int start = findContiguousBlocks(fileSize);
        if (start == -1) {
            System.out.println("Allocation failed: Not enough contiguous space.");
            return false;
        }

        for (int i = start; i < start + fileSize; i++) {
            disk[i] = true;
        }
        fileTable.add(new File(fileName, start, fileSize));
        System.out.println("File " + fileName + " allocated from block " + start + " to " + (start + fileSize - 1));
        return true;
    }

    private int findContiguousBlocks(int size) {
        for (int i = 0; i <= totalBlocks - size; i++) {
            boolean free = true;
            for (int j = i; j < i + size; j++) {
                if (disk[j]) {
                    free = false;
                    break;
                }
            }
            if (free) return i;
        }
        return -1;
    }

    public void displayFileTable() {
        System.out.println("\nFile Table:");
        for (File file : fileTable) {
            System.out.println("File: " + file.name + ", Start Block: " + file.startBlock + ", Length: " + file.length);
        }
    }
}
