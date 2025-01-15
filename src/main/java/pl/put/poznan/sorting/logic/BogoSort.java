package pl.put.poznan.sorting.logic;

import java.util.Random;

/**
 * {@code BogoSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of BogoSort for both int and String arrays.
 * BogoSort works by randomly shuffling the array until it is sorted.
 */
public class BogoSort implements SortingAlgorithm {

    /**
     * A Random instance used for shuffling the array.
     */
    private final Random random = new Random();

    /**
     * Sorts the given int array using the BogoSort algorithm. The algorithm continues shuffling
     * the array randomly until it is sorted or until the specified number of iterations is reached.
     *
     * @param arr        the int array to be sorted
     * @param iterations the maximum number of iterations to attempt; if set to 0 or negative, the algorithm runs 100 000 000 times
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by iteration or time limit
     */
    public long sort(int[] arr, int iterations, long timeLimit) {
        long startTime = System.nanoTime();
        if (iterations <= 0) iterations = 100000000; // Default to 100 million iterations if not specified

        while (!isSorted(arr)) {
            shuffle(arr);
            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || (timePassed >= timeLimit && timeLimit > 0)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Sorts the given String array using the BogoSort algorithm. The algorithm continues shuffling
     * the array randomly until it is sorted or until the specified number of iterations is reached.
     *
     * @param arr        the String array to be sorted
     * @param iterations the maximum number of iterations to attempt; if set to 0 or negative, the algorithm runs 100 000 000 times
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by iteration or time limit
     */
    public long sort(String[] arr, int iterations, long timeLimit) {
        long startTime = System.nanoTime();
        if (iterations <= 0) iterations = 100000000; // Default to 100 million iterations if not specified

        while (!isSorted(arr)) {
            shuffle(arr);
            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || (timePassed >= timeLimit && timeLimit > 0)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Shuffles the elements of the given int array randomly.
     *
     * @param arr the int array to shuffle
     */
    private void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = random.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * Shuffles the elements of the given String array randomly.
     *
     * @param arr the String array to shuffle
     */
    private void shuffle(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = random.nextInt(arr.length);
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * Checks whether the given int array is sorted in non-decreasing order.
     *
     * @param arr the int array to check
     * @return true if the array is sorted; false otherwise
     */
    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

    /**
     * Checks whether the given String array is sorted in non-decreasing order.
     *
     * @param arr the String array to check
     * @return true if the array is sorted; false otherwise
     */
    private boolean isSorted(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) return false;
        }
        return true;
    }
}
