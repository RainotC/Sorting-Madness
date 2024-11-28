package pl.put.poznan.sorting.logic;


public class MergeSort implements algorithm{
    public void sort(int[] arr, int left, int right){
        {
            if (left < right) {

                int middle = left + (right - left) / 2;

                sort(arr, left, middle);
                sort(arr, middle + 1, right);

                merge(arr, left, middle, right);
            }
        }
    }

    static void merge(int arr[], int left, int middle, int right)
    {
        int size1 = middle - left + 1;
        int size2 = right - middle;


        int L[] = new int[size1];
        int R[] = new int[size2];

        for (int i = 0; i < size1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < size2; ++j)
            R[j] = arr[middle + 1 + j];

        int indexL = 0, indexR = 0;

        int indexMerged = left;
        while (indexL < size1 && indexR < size2) {
            if (L[indexL] <= R[indexR]) {
                arr[indexMerged] = L[indexL];
                indexL++;
            }
            else {
                arr[indexMerged] = R[indexR];
                indexR++;
            }
            indexMerged++;
        }

        while (indexL < size1) {
            arr[indexMerged] = L[indexL];
            indexL++;
            indexMerged++;
        }

        while (indexR < size2) {
            arr[indexMerged] = R[indexR];
            indexR++;
            indexMerged++;
        }
    }

}
