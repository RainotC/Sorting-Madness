package pl.put.poznan.sorting.logic;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(int[] arr, int iterations){
        if (iterations == 0) iterations = -1;
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
            }
            if (--iterations == 0 || !swapped) break;
        }
    }

}
