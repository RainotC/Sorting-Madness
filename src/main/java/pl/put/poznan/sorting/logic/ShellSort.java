package pl.put.poznan.sorting.logic;

public class ShellSort implements SortingAlgorithm {

    @Override
    public void sort(int[] arr, int iterations) {
        if (iterations == 0) iterations = -1;
        int len = arr.length;

        for (int gap = len/2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int tmp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > tmp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
            if (--iterations == 0) break;
        }
    }
}
