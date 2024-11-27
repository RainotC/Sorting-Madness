package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.BubbleSort;
import pl.put.poznan.sorting.logic.SortingMadness;
import java.util.Arrays;
import java.util.*;

@RestController
@RequestMapping("/")  // Handle requests to the root
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    // Handle GET request to receive data in the request body
    @GetMapping(produces = "application/json")  // This is a GET request
    public String get(@RequestBody Map<String, Object> jsonMap) {  // Extract JSON from body
        try {
            // Deserialize 'to-sort' field explicitly as a List<Integer>
            ObjectMapper objectMapper = new ObjectMapper();
            List<Integer> toSort = objectMapper.convertValue(jsonMap.get("to-sort"), new TypeReference<>() {});
            String algorithm = (String) jsonMap.get("algorithm");
            int iterations = (int) jsonMap.get("iterations");
            String order = (String) jsonMap.get("order");

            // Log received parameters
            logger.debug("to-sort: {}", toSort);
            logger.debug("algorithm: {}", algorithm);
            logger.debug("iterations: {}", iterations);
            logger.debug("order: {}", order);

            // Start timing the sort operation
            long start = System.nanoTime();
            if ("bubble sort".equalsIgnoreCase(algorithm)) {
                bubbleSort(toSort, "ASC".equalsIgnoreCase(order));
            } else {
                toSort.sort("ASC".equalsIgnoreCase(order) ? Integer::compareTo : Comparator.reverseOrder());
            }
            long end = System.nanoTime();
            long elapsed = end - start;

            // Create response
            Map<String, Object> result = Map.of(
                    "sorted", toSort,
                    "timeElapsed", elapsed + " nanoseconds"
            );

            // Return response as JSON
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            logger.error("Error processing input", e);
            return "{\"error\": \"Invalid input\"}";
        }
    }

    private void bubbleSort(List<Integer> list, boolean ascending) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((ascending && list.get(j) > list.get(j + 1)) ||
                        (!ascending && list.get(j) < list.get(j + 1))) {
                    // Swap elements
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }
}
