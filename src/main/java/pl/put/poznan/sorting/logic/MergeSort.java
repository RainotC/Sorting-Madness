package pl.put.poznan.sorting.logic;

public class MergeSort implements SortingAlgorithm{
    @Override
    public void sort(int[] arr, int iterations) {
        iterations = (iterations == 0) ? -1 : iterations+1;
        mergeSort(arr, 0, arr.length - 1, iterations);
    }

    private void mergeSort(int[] arr, int left, int right, int iterations) {
        if (left < right && iterations-- !=0) {

            int middle = (left + right) / 2;

            mergeSort(arr, left, middle, iterations);
            mergeSort(arr, middle + 1, right, iterations);

            merge(arr, left, middle, right);
        }
    }

    private void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, middle + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
