package pl.put.poznan.sorting.logic;

import java.util.*;

/**
 * {@code SortingMadness} class provides functionality for sorting arrays using various algorithms.
 * It supports sorting both integers and strings, and provides the option to reverse the order.
 */
public class SortingMadness {

    /**
     * The list of sorting algorithms to be used.
     */
    private String[] usedAlgorithms;

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
     * @param timeLimit  the maximum time allowed (in nanoseconds) for the sorting process
     * @return a list of Result objects containing the sorted arrays, execution times,
     *         and the names of the algorithms used
     * @throws IllegalArgumentException if an unknown algorithm name is provided
     */
    public List<Result> sort(int[] toSort, int iterations, String order, long timeLimit) {
        List<Result> results = new ArrayList<>();

        if (usedAlgorithms == null || usedAlgorithms.length == 0) {
            this.usedAlgorithms = chooseSortingAlgorithm(toSort);
        }

        for (String algorithm : usedAlgorithms) {
            SortingAlgorithm sorter = getSortingAlgorithm(algorithm);

            int[] arrayCopy = Arrays.copyOf(toSort, toSort.length); // Copy the array to avoid in-place modifications
            long timePassed = sorter.sort(arrayCopy, iterations, timeLimit * 1000000);
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
     * @param timeLimit  the maximum time allowed (in nanoseconds) for the sorting process
     * @return a list of Result objects containing the sorted arrays, execution times,
     *         and the names of the algorithms used
     * @throws IllegalArgumentException if an unknown algorithm name is provided
     */
    public List<Result> sort(String[] toSort, int iterations, String order, long timeLimit) {
        List<Result> results = new ArrayList<>();

        if (usedAlgorithms == null || usedAlgorithms.length == 0) {
            this.usedAlgorithms = chooseSortingAlgorithm(toSort);
        }

        for (String algorithm : usedAlgorithms) {
            SortingAlgorithm sorter = getSortingAlgorithm(algorithm);

            String[] arrayCopy = Arrays.copyOf(toSort, toSort.length); // Copy the array to avoid in-place modifications
            long timePassed = sorter.sort(arrayCopy, iterations, timeLimit * 1000000);
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

    /**
     * Determines whether an integer array is mostly sorted by counting the number of inversions.
     * An inversion occurs when a pair of adjacent elements is out of order.
     * The array is considered mostly sorted if the number of inversions is less than or equal to
     * a given threshold multiplied by the array's length.
     *
     * @param arr the integer array to check
     * @param threshold the fraction of inversions relative to the array length
     * @return true if the array is mostly sorted, false otherwise
     */
    private static boolean isMostlySorted(int[] arr, double threshold) {
        int inversions = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                inversions++;
            }
        }
        return inversions <= threshold * arr.length;
    }

    /**
     * Determines whether an integer array has a small number of unique values.
     * The array is considered to have few unique values if the number of unique values
     * is less than or equal to the given threshold multiplied by the array's length.
     *
     * @param arr the integer array to check
     * @param threshold the fraction of unique values relative to the array length
     * @return true if the array has few unique values, false otherwise
     */
    private static boolean hasFewUniqueValues(int[] arr, double threshold) {
        Set<Integer> uniqueValues = new HashSet<>();
        for (int num : arr) {
            uniqueValues.add(num);
        }
        return uniqueValues.size() <= threshold * arr.length;
    }

    /**
     * Chooses a sorting algorithm for an integer array based on whether it is mostly sorted
     * or has few unique values. If the array is mostly sorted or has few unique values, it
     * selects Merge Sort; otherwise, Quick Sort is selected.
     *
     * @param arr the integer array to evaluate
     * @return an array of sorting algorithms to be used
     */
    private static String[] chooseSortingAlgorithm(int[] arr) {
        if (isMostlySorted(arr, 0.1) || hasFewUniqueValues(arr, 0.2)) {
            return new String[]{"Merge sort"};
        }
        return new String[]{"Quick sort"};
    }

    /**
     * Determines whether a string array is mostly sorted by counting the number of inversions.
     * An inversion occurs when a pair of adjacent elements is out of order.
     * The array is considered mostly sorted if the number of inversions is less than or equal to
     * a given threshold multiplied by the array's length.
     *
     * @param arr the String array to check
     * @param threshold the fraction of inversions relative to the array length
     * @return true if the array is mostly sorted, false otherwise
     */
    private static boolean isMostlySorted(String[] arr, double threshold) {
        int inversions = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                inversions++;
            }
        }
        return inversions <= threshold * arr.length;
    }

    /**
     * Determines whether a string array has a small number of unique values.
     * The array is considered to have few unique values if the number of unique values
     * is less than or equal to the given threshold multiplied by the array's length.
     *
     * @param arr the String array to check
     * @param threshold the fraction of unique values relative to the array length
     * @return true if the array has few unique values, false otherwise
     */
    private static boolean hasFewUniqueValues(String[] arr, double threshold) {
        Set<String> uniqueValues = new HashSet<>();
        for (String s : arr) {
            uniqueValues.add(s);
        }
        return uniqueValues.size() <= threshold * arr.length;
    }

    /**
     * Chooses a sorting algorithm for a string array based on whether it is mostly sorted
     * or has few unique values. If the array is mostly sorted or has few unique values, it
     * selects Merge Sort; otherwise, Quick Sort is selected.
     *
     * @param arr the String array to evaluate
     * @return an array of sorting algorithms to be used
     */
    private static String[] chooseSortingAlgorithm(String[] arr) {
        if (isMostlySorted(arr, 0.1) || hasFewUniqueValues(arr, 0.2)) {
            return new String[]{"Merge sort"};
        }
        return new String[]{"Quick sort"};
    }
}
