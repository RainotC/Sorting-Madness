package pl.put.poznan.sorting.logic;

/**
 * The {@code BubbleSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of the Bubble Sort algorithm.
 * Bubble Sort repeatedly steps through the array, compares adjacent elements,
 * and swaps them if they are in the wrong order.
 */
public class BubbleSort implements SortingAlgorithm {

    /**
     * Sorts the given array using the Bubble Sort algorithm. The algorithm makes multiple
     * passes through the array, swapping adjacent elements that are in the wrong order.
     * The process stops when the specified number of iterations is reached or
     * no swaps are needed in a pass.
     *
     * @param arr        the array to be sorted
     * @param iterations the maximum number of iterations to perform; if set to 0 or negative,
     *                   the algorithm will run until the array is fully sorted
     */
    @Override
    public long sort(int[] arr, int iterations, long timeLimit) {
        if (iterations == 0) iterations = -1;
        long startTime = System.nanoTime();
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
                long timePassed = System.nanoTime() - startTime;

                if (timeLimit>0 && timePassed>=timeLimit) return timePassed;
            }
            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || !swapped || (timeLimit>0 && timePassed>=timeLimit)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }

}
