import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexedFileAllocation {
    private final List<Block> disk;
    private final Map<String, File> fileTable; // Maps file name to file object

    public IndexedFileAllocation(int totalBlocks) {
        this.disk = new ArrayList<>();
        this.fileTable = new HashMap<>();
        for (int i = 0; i < totalBlocks; i++) {
            disk.add(new Block(i));
        }
    }

    public boolean allocateFile(String fileName, int fileSize) {
        if (fileTable.containsKey(fileName)) {
            System.out.println("File " + fileName + " already exists.");
            return false;
        }

        // Find a free block for the index block
        int indexBlock = findFreeBlock();
        if (indexBlock == -1) {
            System.out.println("Allocation failed: No free blocks for index.");
            return false;
        }

        // Find free blocks for the data
        List<Integer> freeBlocks = findFreeBlocks(fileSize);
        if (freeBlocks.size() < fileSize) {
            System.out.println("Allocation failed: Not enough free blocks.");
            return false;
        }

        // Allocate index block and data blocks
        disk.get(indexBlock).data = "Index for " + fileName;
        File file = new File(fileName, indexBlock);
        file.dataBlocks.addAll(freeBlocks);

        for (int block : freeBlocks) {
            disk.get(block).data = fileName;
        }

        fileTable.put(fileName, file);
        System.out.println("File " + fileName + " allocated at index block: " + indexBlock + ", data blocks: " + freeBlocks);
        return true;
    }

    private int findFreeBlock() {
        for (Block block : disk) {
            if (block.isFree()) {
                return block.blockNumber;
            }
        }
        return -1;
    }

    private List<Integer> findFreeBlocks(int count) {
        List<Integer> freeBlocks = new ArrayList<>();
        for (Block block : disk) {
            if (block.isFree()) {
                freeBlocks.add(block.blockNumber);
                if (freeBlocks.size() == count) break;
            }
        }
        return freeBlocks;
    }

    public void displayDisk() {
        System.out.println("\nDisk Status:");
        for (Block block : disk) {
            System.out.printf("Block %d: Data = %s%n", block.blockNumber, block.data == null ? "Free" : block.data);
        }
    }

    public void displayFileTable() {
        System.out.println("\nFile Table:");
        for (Map.Entry<String, File> entry : fileTable.entrySet()) {
            File file = entry.getValue();
            System.out.println("File: " + file.fileName + ", Index Block: " + file.indexBlock + ", Data Blocks: " + file.dataBlocks);
        }
    }
}

