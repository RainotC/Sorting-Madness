package pl.put.poznan.sorting.logic;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] arr, int iterations) {
        if (iterations == 0) iterations = -1;
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }

            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;

            if (--iterations == 0) break;
        }
    }
}
