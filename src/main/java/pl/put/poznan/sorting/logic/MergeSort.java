package pl.put.poznan.sorting.logic;

/**
 * {@code MergeSort} class implements the {@link SortingAlgorithm} interface
 * and provides an implementation of the Merge Sort algorithm.
 * Merge Sort is a divide-and-conquer algorithm that recursively splits an array into smaller subarrays,
 * sorts them, and then merges them back together.
 */
public class MergeSort implements SortingAlgorithm {

    /**
     * Sorts the given int array using the Merge Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the int array to be sorted
     * @param iterations the maximum number of recursive calls; if set to 0 or negative,
     *                   the algorithm will sort the entire array
     * @param timeLimit  the maximum time allowed for the sorting process in nanoseconds
     * @return the time taken to perform the sort in nanoseconds
     */
    public long sort(int[] arr, int iterations, long timeLimit) {
        long startTime = System.nanoTime();
        iterations = (iterations == 0) ? -1 : iterations + 1;
        mergeSort(arr, 0, arr.length - 1, iterations, timeLimit, startTime);
        return System.nanoTime() - startTime;
    }

    /**
     * Sorts the given String array using the Merge Sort algorithm.
     * The sorting process can be limited by the specified number of iterations.
     *
     * @param arr        the String array to be sorted
     * @param iterations the maximum number of recursive calls; if set to 0 or negative,
     *                   the algorithm will sort the entire array
     * @param timeLimit  the maximum time allowed for the sorting process in nanoseconds
     * @return the time taken to perform the sort in nanoseconds
     */
    public long sort(String[] arr, int iterations, long timeLimit) {
        long startTime = System.nanoTime();
        iterations = (iterations == 0) ? -1 : iterations + 1;
        mergeSort(arr, 0, arr.length - 1, iterations, timeLimit, startTime);
        return System.nanoTime() - startTime;
    }

    /**
     * Recursively splits the array into smaller subarrays and sorts them.
     *
     * @param arr        the array to be sorted
     * @param left       the starting index of the subarray
     * @param right      the ending index of the subarray
     * @param iterations the remaining number of recursive calls allowed
     */
    private void mergeSort(int[] arr, int left, int right, int iterations, long timeLimit, long startTime) {
        if (left < right && iterations-- != 0) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle, iterations, timeLimit, startTime);
            mergeSort(arr, middle + 1, right, iterations, timeLimit, startTime);
            if (System.nanoTime() - startTime >= timeLimit && timeLimit > 0) return;
            merge(arr, left, middle, right);
        }
    }

    /**
     * Recursively splits the String array into smaller subarrays and sorts them.
     *
     * @param arr        the String array to be sorted
     * @param left       the starting index of the subarray
     * @param right      the ending index of the subarray
     * @param iterations the remaining number of recursive calls allowed
     */
    private void mergeSort(String[] arr, int left, int right, int iterations, long timeLimit, long startTime) {
        if (left < right && iterations-- != 0) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle, iterations, timeLimit, startTime);
            mergeSort(arr, middle + 1, right, iterations, timeLimit, startTime);
            if (System.nanoTime() - startTime >= timeLimit && timeLimit > 0) return;
            merge(arr, left, middle, right);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray for int array.
     *
     * @param arr    the original array containing the subarrays
     * @param left   the starting index of the first subarray
     * @param middle the ending index of the first subarray
     * @param right  the ending index of the second subarray
     */
    private void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, middle + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray for String array.
     *
     * @param arr    the original array containing the subarrays
     * @param left   the starting index of the first subarray
     * @param middle the ending index of the first subarray
     * @param right  the ending index of the second subarray
     */
    private void merge(String[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        String[] L = new String[n1];
        String[] R = new String[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, middle + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
