package pl.put.poznan.sorting.logic;


public class ShellSort implements algorithm{
    public void sort(int[] arr, int parameter1, int parameter2) {
        int len = arr.length;

        for (int gap = len/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < len; i += 1)
            {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }

    }
}
