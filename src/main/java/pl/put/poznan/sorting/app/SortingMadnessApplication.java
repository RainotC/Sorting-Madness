package pl.put.poznan.sorting.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.sorting.logic.BubbleSort;
import pl.put.poznan.sorting.logic.MergeSort;
import pl.put.poznan.sorting.logic.QuickSort;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting.rest"})
public class SortingMadnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
        testMergeSort();
    }

    public static void testBubbleSort() {
        
        BubbleSort sorter = new BubbleSort();
        int[] array = {1, 6, 4, 7, 2, 8, 205, 100, 25};
        sorter.sort(array,0,0);
        System.out.println(Arrays.toString(array));

    }

    public static void testQuickSort() {

        QuickSort sorter = new QuickSort();
        int[] array = {1, 6, 4, 7, 2, 8, 205,  100, 25,2};
        sorter.sort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void testMergeSort() {

        MergeSort sorter = new MergeSort();
        int[] array = {1, 6, 4, 7, 2, 8, 205,  100, 25,2};
        sorter.sort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
