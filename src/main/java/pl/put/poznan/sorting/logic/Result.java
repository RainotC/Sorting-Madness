package pl.put.poznan.sorting.logic;

public class Result {
    private long time; // Execution time
    private int[] sortedArray; // Sorted array
    private String algorithm; // Algorithm name

    public Result(long time, int[] sortedArray, String algorithm) {
        this.time = time;
        this.sortedArray = sortedArray;
        this.algorithm = algorithm;
    }

    // Getters
    public long getTime() {
        return time;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
