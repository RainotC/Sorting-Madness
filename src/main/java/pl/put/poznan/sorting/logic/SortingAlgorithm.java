package pl.put.poznan.sorting.logic;

/**
 * {@code SortingAlgorithm} interface defines the contract for sorting algorithms
 * that can be implemented to sort an array with a specified number of iterations.
 */
public interface SortingAlgorithm {

    /**
     * Sorts the given int array in place using the implemented sorting algorithm.
     *
     * @param arr        the int array to be sorted
     * @param iterations the number of iterations to perform during the sorting process;
     *                   if 0 or negative algorithms sort the entire array
     */
    long sort(int[] arr, int iterations, long timeLimit);

    /**
     * Sorts the given String array in place using the implemented sorting algorithm.
     *
     * @param arr        the String array to be sorted
     * @param iterations the number of iterations to perform during the sorting process;
     *                   if 0 or negative algorithms sort the entire array
     */
    long sort(String[] arr, int iterations, long timeLimit);
}
