package pl.put.poznan.sorting.logic;

public class QuickSort implements algorithm {

    public long sort(int[] arr, int QSLow, int QSHigh) {
        long start = System.currentTimeMillis();
        if (QSLow < QSHigh) {
            int pivot = partition(arr, QSLow, QSHigh);

            sort(arr, QSLow, pivot - 1);
            sort(arr, pivot + 1, QSHigh);
        }
        long end = System.currentTimeMillis();
        return end-start;
    }
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                algorithm.swap(arr, i, j);
            }
        }
        algorithm.swap(arr, i + 1, high);
        return i + 1;
    }


}
