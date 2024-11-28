package pl.put.poznan.sorting.logic;


interface algorithm {
    void sort(int[] arr, int parameter1, int parameter2);//Parameters only used in Quicksort and Mergesort, in all other cases just put any int there
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
