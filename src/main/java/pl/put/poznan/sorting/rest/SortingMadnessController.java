package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.Result;
import pl.put.poznan.sorting.logic.SortingMadness;
import java.util.*;
import java.util.stream.Collectors;

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
            int[] toSort = objectMapper.convertValue(jsonMap.get("to-sort"), int[].class);
            String[] algorithms = objectMapper.convertValue(jsonMap.get("algorithms"), String[].class);
            int iterations = (int) jsonMap.get("iterations");
            String order = (String) jsonMap.get("order");

            // Log received parameters
            logger.debug("to-sort: {}", toSort);
            logger.debug("algorithms: {}", (Object) algorithms);
            logger.debug("iterations: {}", iterations);
            logger.debug("order: {}", order);

            SortingMadness sortingMadness = new SortingMadness(algorithms);
            List<Result> results = sortingMadness.sort(toSort, iterations, order);

            // Create response in the specified format
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> resultMap = new HashMap<>();

            for (Result result : results) {
                Map<String, Object> algorithmResult = new HashMap<>();
                algorithmResult.put("timeElapsed [ns]", result.getTime());
                algorithmResult.put("sorted", result.getSortedArray());
                resultMap.put(result.getAlgorithm(), algorithmResult);
            }

            response.put("results", resultMap);

            // Return response as JSON
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            logger.error("Error processing input", e);
            return "{\"error\": \"Invalid input\"}";
        }
    }
}
