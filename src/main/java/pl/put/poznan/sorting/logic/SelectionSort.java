package pl.put.poznan.sorting.logic;


public class SelectionSort implements algorithm {
    public long sort(int[] arr, int parameter1, int parameter2) {
        long start = System.currentTimeMillis();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int min_index = i;

            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }

            algorithm.swap(arr, i, min_index);
        }
        long end = System.currentTimeMillis();
        return end-start;
    }
}
