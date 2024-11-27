package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingMadness {

    private final String[] usedAlgorithms;

    public SortingMadness(String[] usedAlgorithms) {
        this.usedAlgorithms = usedAlgorithms;
    }

    public List<Result> sort(int[] toSort) {
        List<Result> results = new ArrayList<>();

        for (String algorithm : usedAlgorithms) {
            SortingAlgorithm sorter;
            switch (algorithm.toLowerCase()) {
                case "bubble sort" -> sorter = new BubbleSort();
                case "quick sort" -> sorter = new QuickSort();
                default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
            }

            int[] arrayCopy = Arrays.copyOf(toSort, toSort.length); // Copy the array to avoid in-place modifications
            long startTime = System.nanoTime(); // Start timing
            sorter.sort(arrayCopy);
            long endTime = System.nanoTime(); // End timing

            results.add(new Result(endTime - startTime, arrayCopy, algorithm)); // Add result
        }

        return results;
    }
}
