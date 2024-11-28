package pl.put.poznan.sorting.logic;


public class BubbleSort implements algorithm {
    public long sort(int[] arr, int QSLow, int QSHigh) {
        long start = System.currentTimeMillis();
        int i, j, temp;
        int len = arr.length;
        boolean swapped;
        for (i = 0; i < len - 1; i++) {
            swapped = false;
            for (j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        long end = System.currentTimeMillis();
        return end-start;
    }

}
