package pl.put.poznan.sorting.logic;

import java.util.Random;

public class BogoSort implements SortingAlgorithm {
    private final Random random = new Random();

    @Override
    public void sort(int[] arr, int iterations) {
        if (iterations == 0) iterations = -1;
        while (!isSorted(arr)) {
            shuffle(arr);
            if (--iterations == 0) break;
        }
    }

    private void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = random.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
}
