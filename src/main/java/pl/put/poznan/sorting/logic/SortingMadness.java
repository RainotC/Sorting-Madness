package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code SortingMadness} class provides functionality for sorting arrays using various algorithms.
 * It supports sorting both integers and strings, and provides the option to reverse the order.
 */
public class SortingMadness {

    /**
     * The list of sorting algorithms to be used.
     */
    private final String[] usedAlgorithms;

    /**
     * Constructs a new {@code SortingMadness} instance.
     *
     * @param usedAlgorithms an array of algorithm names to be used for sorting
     */
    public SortingMadness(String[] usedAlgorithms) {
        this.usedAlgorithms = usedAlgorithms;
    }

    /**
     * Reverses the given integer array in place.
     *
     * @param array the int array to be reversed
     */
    private static void reverseInPlace(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            // Swap elements
            int temp = array[left];
            array[left++] = array[right];
            array[right--] = temp;
        }
    }

    /**
     * Reverses the given array in place (used for both int[] and String[]).
     *
     * @param array the array to be reversed
     */
    private static <T> void reverseInPlace(T[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            // Swap elements
            T temp = array[left];
            array[left++] = array[right];
            array[right--] = temp;
        }
    }

    /**
     * Sorts the given int array using the specified algorithms and configurations.
     *
     * @param toSort     the int array to be sorted
     * @param iterations the number of iterations to use in the sorting algorithm
     * @param order      the desired order of the result ("asc" for ascending, "desc" for descending)
     * @return a list of Result objects containing the sorted arrays, execution times,
     *         and the names of the algorithms used
     * @throws IllegalArgumentException if an unknown algorithm name is provided
     */
    public List<Result> sort(int[] toSort, int iterations, String order, long timeLimit) {
        List<Result> results = new ArrayList<>();

        for (String algorithm : usedAlgorithms) {
            SortingAlgorithm sorter = getSortingAlgorithm(algorithm);

            int[] arrayCopy = Arrays.copyOf(toSort, toSort.length); // Copy the array to avoid in-place modifications
            long timePassed = sorter.sort(arrayCopy, iterations, timeLimit);
            if (order.equalsIgnoreCase("desc")) reverseInPlace(arrayCopy);

            results.add(new Result(timePassed, arrayCopy, algorithm)); // Add result
        }

        return results;
    }

    /**
     * Sorts the given String array using the specified algorithms and configurations.
     *
     * @param toSort     the String array to be sorted
     * @param iterations the number of iterations to use in the sorting algorithm
     * @param order      the desired order of the result ("asc" for ascending, "desc" for descending)
     * @return a list of Result objects containing the sorted arrays, execution times,
     *         and the names of the algorithms used
     * @throws IllegalArgumentException if an unknown algorithm name is provided
     */
    public List<Result> sort(String[] toSort, int iterations, String order, long timeLimit) {
        List<Result> results = new ArrayList<>();

        for (String algorithm : usedAlgorithms) {
            SortingAlgorithm sorter = getSortingAlgorithm(algorithm);

            String[] arrayCopy = Arrays.copyOf(toSort, toSort.length); // Copy the array to avoid in-place modifications
            long timePassed = sorter.sort(arrayCopy, iterations, timeLimit);
            if (order.equalsIgnoreCase("desc")) reverseInPlace(arrayCopy);

            results.add(new Result(timePassed, arrayCopy, algorithm)); // Add result
        }

        return results;
    }

    /**
     * Retrieves the appropriate SortingAlgorithm implementation based on the algorithm name.
     *
     * @param algorithm the name of the sorting algorithm
     * @return an instance of the corresponding SortingAlgorithm
     * @throws IllegalArgumentException if the algorithm name is unknown
     */
    private static SortingAlgorithm getSortingAlgorithm(String algorithm) {
        SortingAlgorithm sorter;
        switch (algorithm.toLowerCase()) {
            case "bubble sort" -> sorter = new BubbleSort();
            case "quick sort" -> sorter = new QuickSort();
            case "merge sort" -> sorter = new MergeSort();
            case "bogo sort" -> sorter = new BogoSort();
            case "selection sort" -> sorter = new SelectionSort();
            case "shell sort" -> sorter = new ShellSort();
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
        return sorter;
    }
}
