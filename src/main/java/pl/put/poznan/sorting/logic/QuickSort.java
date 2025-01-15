package pl.put.poznan.sorting.logic;

/**
 * {@code QuickSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of the Quick Sort algorithm.
 * Quick Sort is a divide-and-conquer algorithm that sorts an array by selecting a pivot element
 * and partitioning the other elements into two subarrays based on their relation to the pivot.
 */
public class QuickSort implements SortingAlgorithm {

    /**
     * Sorts the given int array using the Quick Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the int array to be sorted
     * @param iterations the maximum number of recursive calls allowed;
     *                   if set to 0 or negative, the algorithm sorts the entire array
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by time limit
     */
    public long sort(int[] arr, int iterations, long timeLimit) {
        if (iterations == 0) iterations = -1;
        long startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1, iterations, startTime, timeLimit);
        return System.nanoTime() - startTime;
    }

    /**
     * Sorts the given String array using the Quick Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the String array to be sorted
     * @param iterations the maximum number of recursive calls allowed;
     *                   if set to 0 or negative, the algorithm sorts the entire array
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     * @return the time taken in nanoseconds to sort the array, or the time elapsed if the sorting is stopped by time limit
     */
    public long sort(String[] arr, int iterations, long timeLimit) {
        if (iterations == 0) iterations = -1;
        long startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1, iterations, startTime, timeLimit);
        return System.nanoTime() - startTime;
    }

    /**
     * Recursively sorts subarrays by partitioning them around a pivot element for int array.
     *
     * @param arr        the array to be sorted
     * @param low        the starting index of the subarray
     * @param high       the ending index of the subarray
     * @param iterations the remaining number of recursive calls allowed
     * @param startTime  the starting time for time limit check
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     */
    private void quickSort(int[] arr, int low, int high, int iterations, long startTime, long timeLimit) {
        if (low < high && iterations-- != 0) {
            if (timeLimit > 0 && System.nanoTime() - startTime >= timeLimit) {
                return;
            }
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1, iterations, startTime, timeLimit);
            quickSort(arr, pivot + 1, high, iterations, startTime, timeLimit);
        }
    }

    /**
     * Recursively sorts subarrays by partitioning them around a pivot element for String array.
     *
     * @param arr        the array to be sorted
     * @param low        the starting index of the subarray
     * @param high       the ending index of the subarray
     * @param iterations the remaining number of recursive calls allowed
     * @param startTime  the starting time for time limit check
     * @param timeLimit  the maximum time allowed in milliseconds for the sorting process; if 0 or negative algorithms sort without time limit
     */
    private void quickSort(String[] arr, int low, int high, int iterations, long startTime, long timeLimit) {
        if (low < high && iterations-- != 0) {
            if (timeLimit > 0 && System.nanoTime() - startTime >= timeLimit) {
                return;
            }
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1, iterations, startTime, timeLimit);
            quickSort(arr, pivot + 1, high, iterations, startTime, timeLimit);
        }
    }

    /**
     * Partitions the int array subarray into two parts around a pivot element.
     * Elements smaller than the pivot are moved to the left of the pivot,
     * and elements greater than or equal to the pivot are moved to the right.
     *
     * @param arr  the array to partition
     * @param low  the starting index of the subarray
     * @param high the ending index of the subarray
     * @return the index of the pivot after partitioning
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    /**
     * Partitions the String array subarray into two parts around a pivot element.
     * Strings smaller than the pivot are moved to the left of the pivot,
     * and strings greater than or equal to the pivot are moved to the right.
     *
     * @param arr  the array to partition
     * @param low  the starting index of the subarray
     * @param high the ending index of the subarray
     * @return the index of the pivot after partitioning
     */
    private int partition(String[] arr, int low, int high) {
        String pivot = arr[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                String tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        String tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }
}
