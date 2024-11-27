package pl.put.poznan.sorting.logic;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, int iterations) {
        if (iterations == 0) iterations = -1;
        quickSort(array, 0, array.length - 1, iterations);
    }

    private void quickSort(int[] array, int low, int high, int iterations) {
        if (low < high && iterations-- != 0) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1, iterations);
            quickSort(array, pi + 1, high, iterations);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
