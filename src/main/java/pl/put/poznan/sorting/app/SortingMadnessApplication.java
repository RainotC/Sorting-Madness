package pl.put.poznan.sorting.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.sorting.logic.BubbleSort;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting.rest"})
public class SortingMadnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
        testBubbleSort();
    }

    public static void testBubbleSort() {
        
        BubbleSort sorter = new BubbleSort();
        int[] array = {1, 6, 4, 7, 2, 8, 205};
        sorter.sort(array, array.length);
        System.out.println(Arrays.toString(array));

    }
}
