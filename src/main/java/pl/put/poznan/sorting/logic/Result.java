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
     * The result int array from the sorting algorithm.
     */
    private int[] sortedArray;

    /**
     * The result String array from the sorting algorithm.
     */
    private String[] sortedStringArray;

    /**
     * The name of the algorithm used for sorting.
     */
    private String algorithm; // Algorithm name

    /**
     * Constructs a {@code Result} object with the specified execution time,
     * sorted int array, and algorithm name.
     *
     * @param time        the execution time of the sorting operation in nanoseconds
     * @param sortedArray the int array sorted by the algorithm
     * @param algorithm   the name of the sorting algorithm used
     */
    public Result(long time, int[] sortedArray, String algorithm) {
        this.time = time;
        this.sortedArray = sortedArray;
        this.algorithm = algorithm;
    }

    /**
     * Constructs a {@code Result} object with the specified execution time,
     * sorted String array, and algorithm name.
     *
     * @param time        the execution time of the sorting operation in nanoseconds
     * @param sortedArray the String array sorted by the algorithm
     * @param algorithm   the name of the sorting algorithm used
     */
    public Result(long time, String[] sortedArray, String algorithm) {
        this.time = time;
        this.sortedStringArray = sortedArray;
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
     * Returns the sorted int array.
     *
     * @return the sorted int array
     */
    public int[] getSortedArray() {
        return sortedArray;
    }

    /**
     * Returns the sorted String array.
     *
     * @return the sorted String array
     */
    public String[] getSortedStringArray() {
        return sortedStringArray;
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
