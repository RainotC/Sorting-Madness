package pl.put.poznan.sorting.logic;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(int[] arr, int iterations) {
        if (iterations == 0) iterations = -1;
        quickSort(arr, 0, arr.length - 1, iterations);
    }

    private void quickSort(int[] arr, int low, int high, int iterations) {
        if (low < high && iterations-- != 0) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1, iterations);
            quickSort(arr, pivot + 1, high, iterations);
        }
    }

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
}
