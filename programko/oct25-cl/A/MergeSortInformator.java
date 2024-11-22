public class MergeSortInformator {
    private int comparisonCount;
    private int mergeCount;

    private long start;
    private double duration;

    MergeSortInformator() {
        this.comparisonCount = 0;
        this.mergeCount = 0;
        this.start = 0;
        this.duration = 0.0;
    }

    public void start() {
        this.start = System.nanoTime();
    }
    public void end() {
        this.duration = (System.nanoTime() - this.start) / 1000000.0; //milliseconds
    }

    public void addCompare() {
        this.comparisonCount++;
    }
    public void addMergeCall() {
        this.mergeCount++;
    }

    @Override
    public String toString() {
        return String.format("merge %f milliseconds, %d comparisons, %d call count", this.duration, this.comparisonCount, this.mergeCount);
    }
}
