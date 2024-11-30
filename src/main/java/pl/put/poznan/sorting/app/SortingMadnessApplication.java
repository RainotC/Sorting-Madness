package pl.put.poznan.sorting.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code SortingMadnessApplication} class serves as the entry point for the
 * Sorting Madness Spring Boot application. It initializes the application context
 * and starts the embedded web server.
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting.rest"})
public class SortingMadnessApplication {
    /**
     * The main method that starts the Sorting Madness application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
