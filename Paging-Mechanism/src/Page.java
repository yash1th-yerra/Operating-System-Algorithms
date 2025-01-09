public class Page {

    private int pageNumber;
    private int frameNumber;

    public Page(int pageNumber,int frameNumber){
        this.pageNumber = pageNumber;
        this.frameNumber = frameNumber;
    }


    public int getPageNumber(){
        return pageNumber;
    }
    public int getFrameNumber(){
        return frameNumber;
    }

    @Override
    public String toString() {
        return "Page "+pageNumber+" -> Frame "+frameNumber;
    }
}
