public class Block {
    int blockNumber;
    String data;

    public Block(int blockNumber) {
        this.blockNumber = blockNumber;
        this.data = null;
    }

    public boolean isFree() {
        return data == null;
    }
}

