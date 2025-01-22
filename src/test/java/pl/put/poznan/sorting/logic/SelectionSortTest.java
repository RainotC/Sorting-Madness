package pl.put.poznan.sorting.logic;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {
    private SelectionSort selectionSort;

    @BeforeEach
    void setUp() {
        selectionSort = new SelectionSort();
    }

    @org.junit.jupiter.api.Test
    void correctNoLimitsSort() {
        int[] arr = {34, 12, 78, 23, 56, 89, 45, 67, 90, 11, 4, 8, 19, 36, 72, 81, 15, 29, 50, 62};

        int[] expected = arr.clone();
        Arrays.sort(expected); // Sort the expected array using Arrays.sort()

        selectionSort.sort(arr, 0, 0);

        assertArrayEquals(expected, arr);
    }

    @org.junit.jupiter.api.Test
    void correctLimitsSort() {
        int[] arr = {34, 12, 78, 23, 56, 89, 45, 67, 90, 11, 4, 8, 19, 36, 72, 81, 15, 29, 50, 62};

        int[] expected = arr.clone();
        Arrays.sort(expected); // Sort the expected array using Arrays.sort()

        selectionSort.sort(arr, 1000, 1000000000);

        assertArrayEquals(expected, arr);
    }

    @org.junit.jupiter.api.Test
    void incorrectIterationLimitSort() {
        int[] arr = {34, 12, 78, 23, 56, 89, 45, 67, 90, 11, 4, 8, 19, 36, 72, 81, 15, 29, 50, 62};

        int[] expected = {4, 12, 78, 23, 56, 89, 45, 67, 90, 11, 34, 8, 19, 36, 72, 81, 15, 29, 50, 62};

        selectionSort.sort(arr, 1, 0);

        assertArrayEquals(expected, arr);
    }

    @org.junit.jupiter.api.Test
    void incorrectTimeLimitSort() {
        int[] arr = {34, 12, 78, 23, 56, 89, 45, 67, 90, 11, 4, 8, 19, 36, 72, 81, 15, 29, 50, 62};

        int[] expected = arr.clone();
        Arrays.sort(expected); // Sort the expected array using Arrays.sort()

        selectionSort.sort(arr, 0, 10);

        assertFalse(Arrays.equals(expected, arr));
    }

    @org.junit.jupiter.api.Test
    void correctNoLimitsSortStrings() {
        String[] arr = {"apple", "orange", "banana", "grape", "pear", "kiwi",
                "mango", "pineapple", "peach", "cherry", "plum", "lemon",
                "lime", "coconut", "papaya", "blueberry", "raspberry",
                "strawberry", "blackberry", "watermelon"};

        String[] expected = arr.clone();
        Arrays.sort(expected); // Sort the expected array using Arrays.sort()

        selectionSort.sort(arr, 0, 0); // Sort the array using your custom selectionSort method

        assertArrayEquals(expected, arr); // Assert that the arrays are equal
    }
}