package pl.put.poznan.sorting.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.sorting.logic.*;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting.rest"})
public class SortingMadnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
        testBogoSort();
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

    public static void testShellSort() {

        ShellSort sorter = new ShellSort();
        int[] array = {1, 6, 4, 7, 2, 8, 205,  100, 25,2};
        sorter.sort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void testSelectionSort() {

        SelectionSort sorter = new SelectionSort();
        int[] array = {1, 6, 4, 7, 2, 8, 205,  100, 25,2};
        sorter.sort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void testBogoSort() {

        BogoSort sorter = new BogoSort();
        int[] array = {1, 6, 4, 200, 7, 13, 254};
        sorter.sort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

}
