package pl.put.poznan.sorting.logic;

/**
 * {@code SelectionSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of the Selection Sort algorithm.
 * Selection Sort works by repeatedly finding the smallest element in the unsorted portion
 * of the array and moving it to the beginning.
 */
public class SelectionSort implements SortingAlgorithm {

    /**
     * Sorts the given int array using the Selection Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the int array to be sorted
     * @param iterations the maximum number of iterations to perform;
     *                   if set to 0 or negative, the algorithm sorts the entire array
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by time limit
     */
    public long sort(int[] arr, int iterations, long timeLimit) {
        if (iterations == 0) iterations = -1;
        long startTime = System.nanoTime();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }

            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;

            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || (timePassed >= timeLimit && timeLimit > 0)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Sorts the given String array using the Selection Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the String array to be sorted
     * @param iterations the maximum number of iterations to perform;
     *                   if set to 0 or negative, the algorithm sorts the entire array
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by time limit
     */
    public long sort(String[] arr, int iterations, long timeLimit) {
        if (iterations == 0) iterations = -1;
        long startTime = System.nanoTime();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < len; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) minIndex = j;
            }

            String tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;

            long timePassed = System.nanoTime() - startTime;
            if (--iterations == 0 || (timePassed >= timeLimit && timeLimit > 0)) return timePassed;
        }
        return System.nanoTime() - startTime;
    }
}
