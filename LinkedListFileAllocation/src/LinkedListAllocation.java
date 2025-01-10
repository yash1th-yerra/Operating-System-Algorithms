import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedListAllocation {
    private final List<Block> disk;
    private final Map<String, Integer> fileTable; // Maps file name to starting block

    public LinkedListAllocation(int totalBlocks) {
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

        List<Integer> freeBlocks = findFreeBlocks(fileSize);
        if (freeBlocks.size() < fileSize) {
            System.out.println("Allocation failed: Not enough free blocks.");
            return false;
        }

        for (int i = 0; i < fileSize; i++) {
            int currentBlock = freeBlocks.get(i);
            disk.get(currentBlock).data = fileName;
            if (i < fileSize - 1) {
                disk.get(currentBlock).nextBlock = freeBlocks.get(i + 1);
            }
        }

        fileTable.put(fileName, freeBlocks.get(0));
        System.out.println("File " + fileName + " allocated at blocks: " + freeBlocks);
        return true;
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
            System.out.printf("Block %d: Data = %s, Next Block = %s%n",
                    block.blockNumber,
                    block.data == null ? "Free" : block.data,
                    block.nextBlock == null ? "None" : block.nextBlock);
        }
    }

    public void displayFileTable() {
        System.out.println("\nFile Table:");
        for (Map.Entry<String, Integer> entry : fileTable.entrySet()) {
            System.out.println("File: " + entry.getKey() + ", Starting Block: " + entry.getValue());
        }
    }
}


