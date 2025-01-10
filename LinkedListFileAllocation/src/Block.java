public class Block {
    int blockNumber;
    String data;
    Integer nextBlock; // Pointer to the next block

    public Block(int blockNumber) {
        this.blockNumber = blockNumber;
        this.data = null;
        this.nextBlock = null;
    }

    public boolean isFree() {
        return data == null;
    }
}