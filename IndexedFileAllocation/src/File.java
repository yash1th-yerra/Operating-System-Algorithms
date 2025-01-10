import java.util.ArrayList;
import java.util.List;

public class File {
    String fileName;
    int indexBlock;
    List<Integer> dataBlocks;

    public File(String fileName, int indexBlock) {
        this.fileName = fileName;
        this.indexBlock = indexBlock;
        this.dataBlocks = new ArrayList<>();
    }
}