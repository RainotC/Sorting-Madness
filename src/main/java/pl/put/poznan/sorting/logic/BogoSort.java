package pl.put.poznan.sorting.logic;

public class BogoSort implements algorithm {
    public long sort(int[] arr, int parameter1, int parameter2) {
        long start = System.currentTimeMillis();
        while (!isSorted(arr)){
            shuffle(arr);
        }
        long end = System.currentTimeMillis();
        return end-start;
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
