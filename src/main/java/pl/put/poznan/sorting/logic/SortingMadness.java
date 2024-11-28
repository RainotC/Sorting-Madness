package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingMadness {

    private final String[] usedAlgorithms;

    public SortingMadness(String[] usedAlgorithms) {
        this.usedAlgorithms = usedAlgorithms;
    }

    private static void reverseInPlace(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            // Swap elements
            int temp = array[left];
            array[left++] = array[right];
            array[right--] = temp;
        }
    }

    public List<Result> sort(int[] toSort, int iterations, String order) {
        List<Result> results = new ArrayList<>();

        for (String algorithm : usedAlgorithms) {
            SortingAlgorithm sorter = getSortingAlgorithm(algorithm);

            int[] arrayCopy = Arrays.copyOf(toSort, toSort.length); // Copy the array to avoid in-place modifications
            long startTime = System.nanoTime(); // Start timing
            sorter.sort(arrayCopy,iterations);
            if (order.equalsIgnoreCase("desc")) reverseInPlace((arrayCopy));
            long endTime = System.nanoTime(); // End timing

            results.add(new Result(endTime - startTime, arrayCopy, algorithm)); // Add result
        }

        return results;
    }

    private static SortingAlgorithm getSortingAlgorithm(String algorithm) {
        SortingAlgorithm sorter;
        switch (algorithm.toLowerCase()) {
            case "bubble sort" -> sorter = new BubbleSort();
            case "quick sort" -> sorter = new QuickSort();
            case "merge sort" -> sorter = new MergeSort();
            case "bogo sort" -> sorter = new BogoSort();
            case "selection sort" -> sorter = new SelectionSort();
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
        return sorter;
    }
}
