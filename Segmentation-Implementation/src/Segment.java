public class Segment {


    private String name;        // segment name such as code,data and stack
    private int base;           // base address of segment
    private int limit;          // size/ length of segment


    public Segment(String name, int base, int limit) {
        this.name = name;
        this.base = base;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public int getBase() {
        return base;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isAddressInSegment(int logicalAddress) {
        return logicalAddress >= 0 && logicalAddress < limit;
    }

    public int translateAddress(int logicalAddress) {
        return base + logicalAddress;
    }

    @Override
    public String toString() {
        return "Segment " + name + ": Base = " + base + ", Limit = " + limit;
    }

}
