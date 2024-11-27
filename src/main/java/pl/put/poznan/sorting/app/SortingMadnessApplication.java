package pl.put.poznan.sorting.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.sorting.logic.BubbleSort;
import pl.put.poznan.sorting.logic.QuickSort;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting.rest"})
public class SortingMadnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
