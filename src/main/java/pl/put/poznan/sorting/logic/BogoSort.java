package pl.put.poznan.sorting.logic;

import java.util.Arrays;

public class BogoSort implements algorithm {
    public void sort(int[] arr, int parameter1, int parameter2) {
        while (!isSorted(arr)){
            shuffle(arr);
        }
    }

    public void shuffle(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            algorithm.swap(arr, i, (int) (Math.random() * arr.length ));
        }
    }
    boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i - 1])
                return false;
        return true;
    }
}
