package pl.put.poznan.sorting.logic;

/**
 * {@code Result} class encapsulates the result of a sorting operation,
 * including the execution time, the sorted array, and the name of the algorithm used.
 */
public class Result {
    /**
     * The execution time of the sorting operation, in nanoseconds.
     */
    private long time;

    /**
     * The result array from the sorting algorithm.
     */
    private int[] sortedArray;

    /**
     * The name of the algorithm used for sorting.
     */
    private String algorithm; // Algorithm name

    /**
     * Constructs a {@code Result} object with the specified execution time,
     * sorted array, and algorithm name.
     *
     * @param time        the execution time of the sorting operation in nanoseconds
     * @param sortedArray the array sorted by the algorithm
     * @param algorithm   the name of the sorting algorithm used
     */
    public Result(long time, int[] sortedArray, String algorithm) {
        this.time = time;
        this.sortedArray = sortedArray;
        this.algorithm = algorithm;
    }

    /**
     * Returns the execution time of the sorting operation.
     *
     * @return the execution time in nanoseconds
     */
    public long getTime() {
        return time;
    }

    /**
     * Returns the sorted array.
     *
     * @return the sorted array
     */
    public int[] getSortedArray() {
        return sortedArray;
    }

    /**
     * Returns the name of the sorting algorithm used.
     *
     * @return the algorithm name
     */
    public String getAlgorithm() {
        return algorithm;
    }
}
