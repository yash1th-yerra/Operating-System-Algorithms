public class File {
    String name;
    int startBlock;
    int length;

    public File(String name, int startBlock, int length) {
        this.name = name;
        this.startBlock = startBlock;
        this.length = length;
    }
}
