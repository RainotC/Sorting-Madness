package pl.put.poznan.sorting.logic;

import java.util.Random;

/**
 * {@code BogoSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of BogoSort.
 * BogoSort works by randomly shuffling the array until it is sorted.
 */
public class BogoSort implements SortingAlgorithm {

    /**
     * A Random instance used for shuffling the array.
     */
    private final Random random = new Random();

    /**
     * Sorts the given array using the BogoSort algorithm. The algorithm continues shuffling
     * the array randomly until it is sorted or until the specified number of iterations is reached.
     *
     * @param arr        the array to be sorted
     * @param iterations the maximum number of iterations to attempt; if set to 0 or negative, the algorithm runs 100000000 times
     */
    @Override
    public void sort(int[] arr, int iterations) {
        if (iterations <= 0) iterations = 100000000;
        while (!isSorted(arr)) {
            shuffle(arr);
            if (--iterations == 0) break;
        }
    }

    /**
     * Shuffles the elements of the given array randomly.
     *
     * @param arr the array to shuffle
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
     * Checks whether the given array is sorted in non-decreasing order.
     *
     * @param arr the array to check
     * @return true if the array is sorted; false otherwise
     */
    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
}
