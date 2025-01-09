import java.util.ArrayList;
import java.util.List;

public class SegmentationSystem {
    private List<Segment> segments;

    public SegmentationSystem(){
        this.segments = new ArrayList<>();
    }


    // adds a segment

    public void addSegment(String name, int base,int limit){
        segments.add(new Segment(name,base,limit));
        System.out.println("Added Segment "+name+" (Base: "+base+" , limit: "+limit);

    }

    // translates a logical address in a given segment of segment table into physical address

    public int translateAddress(String segmentName,int logicalAddress){
        for(Segment segment: segments){
            if(segment.getName().equals(segmentName)){
                return segment.translateAddress(logicalAddress);
            }else{
                throw new IllegalArgumentException("Logical Address out of bounds for segment");
            }
        }
        throw new IllegalArgumentException("Segment not found "+segmentName);
    }

    // displays all segment

    public void displaySegments(){
        System.out.println("Segments:");
        for(Segment segment:segments){
            System.out.println(segment);
        }
    }
}
