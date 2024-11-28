package pl.put.poznan.sorting.logic;


import java.util.Optional;

interface algorithm {
    void sort(int[] arr, int QSLow, int QSHigh);//QSLow and QSHigh only important in Quicksort, in all other cases just put any int there.
}
