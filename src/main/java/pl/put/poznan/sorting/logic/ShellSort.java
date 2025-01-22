package pl.put.poznan.sorting.logic;

/**
 * {@code ShellSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of the Shell Sort algorithm.
 * Shell Sort is an optimization of insertion sort that allows the exchange of items
 * that are far apart by using a gap sequence to divide the array into subarrays.
 */
public class ShellSort implements SortingAlgorithm {

    /**
     * Sorts the given int array using the Shell Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the int array to be sorted
     * @param iterations the maximum number of iterations to perform;
     *                   if set to 0 or negative, the algorithm sorts the entire array
     * @param timeLimit  the maximum time allowed in nanoseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by time limit
     */
    public long sort(int[] arr, int iterations, long timeLimit) {
        long startTime = System.nanoTime();
        if (iterations == 0) iterations = -1;
        int len = arr.length;

        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int tmp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > tmp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || (timePassed >= timeLimit && timeLimit > 0)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Sorts the given String array using the Shell Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the String array to be sorted
     * @param iterations the maximum number of iterations to perform;
     *                   if set to 0 or negative, the algorithm sorts the entire array
     * @param timeLimit  the maximum time allowed in nanoseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by time limit
     */
    public long sort(String[] arr, int iterations, long timeLimit) {
        long startTime = System.nanoTime();
        if (iterations == 0) iterations = -1;
        int len = arr.length;

        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                String tmp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap].compareTo(tmp) > 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || (timePassed >= timeLimit && timeLimit > 0)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }
}
