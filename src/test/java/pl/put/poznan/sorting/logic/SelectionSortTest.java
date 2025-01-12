package pl.put.poznan.sorting.logic;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @org.junit.jupiter.api.Test
    void selectionSort() {
        SelectionSort selectionSort = new SelectionSort();
        int[] arr = {5, 3, 2, 4, 1};
        selectionSort.sort(arr, 0, 0);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @org.junit.jupiter.api.Test
    void selectionSort_already_sorted() {
        SelectionSort selectionSort = new SelectionSort();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        selectionSort.sort(arr, 0, 0);
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}, arr);
    }

}