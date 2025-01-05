package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.Result;
import pl.put.poznan.sorting.logic.SortingMadness;
import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * The {@code SortingMadnessController} class is a REST controller that handles requests
 * for sorting operations. It allows users to send data and parameters via a GET request
 * and receive sorted results in JSON format.
 */
@RestController
@RequestMapping("/")  // Handle requests to the root
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    /**
     * Handles GET requests for sorting data.
     * Expects a JSON body with the following fields:
     * <ul>
     *     <li>{@code to-sort} - an array of integers to be sorted</li>
     *     <li>{@code algorithms} - an array of sorting algorithm names to use</li>
     *     <li>{@code iterations} - the maximum number of iterations to perform</li>
     *     <li>{@code order} - the sorting order, e.g., ascending or descending</li>
     * </ul>
     *
     * Example request body:
     * <pre>
     * {
     *     "to-sort": [5, 3, 8, 6, 2],
     *     "algorithms": ["BubbleSort", "QuickSort"],
     *     "iterations": 100,
     *     "order": "ascending"
     * }
     * </pre>
     *
     * @param jsonMap a map containing the input parameters as JSON
     * @return a JSON response containing the sorted array and elapsed time in nanoseconds
     *         or an error message if the input is invalid
     */
    @GetMapping(produces = "application/json")  // This is a GET request
    public String get(@RequestBody Map<String, Object> jsonMap) {  // Extract JSON from body
        logger.info("Connenction request recieved");
        // Deserialize 'to-sort' field explicitly as a List<Integer>
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!jsonMap.containsKey("to-sort") || !jsonMap.containsKey("algorithms") ||
                    !jsonMap.containsKey("iterations") || !jsonMap.containsKey("order")) {
                throw new IllegalArgumentException("Missing required fields: 'to-sort', 'algorithms', 'iterations', 'order'");
            }
            List<Map<String, Object>> toSort;
            String field;
            String[] algorithms;
            int iterations;
            String order;
            try {
                field = (String) jsonMap.get("field");
                toSort = objectMapper.convertValue(jsonMap.get("to-sort"), new TypeReference<List<Map<String, Object>>>() {});
                algorithms = objectMapper.convertValue(jsonMap.get("algorithms"), String[].class);
                iterations = (int) jsonMap.get("iterations");
                order = (String) jsonMap.get("order");
                //catching errors
//                if (toSort.length<1)throw new IllegalArgumentException("List of numbers to sort cannot be empty");
                if (algorithms.length<1)throw new IllegalArgumentException("List of algorithms cannot be empty");
            }catch(IllegalArgumentException | ClassCastException e){
                logger.error("Invalid data types in input", e); // i don't know if it's necessary if throws show on log anyway
                return "{\"error\": \"Invalid data types in input: "+e.getMessage()+"\"}"; //showing to client
            }
            // Log received parameters
            logger.debug("to-sort: {}", toSort);
            logger.debug("algorithms: {}", (Object) algorithms);
            logger.debug("iterations: {}", iterations);
            logger.debug("order: {}", order);

            logger.info("Started sorting");
            SortingMadness sortingMadness = new SortingMadness(algorithms);
            List<Integer> fieldValuesList = new ArrayList<>();
            for (Map<String, Object> item : toSort) {
                if (!item.containsKey(field)) {
                    throw new IllegalArgumentException("Field '" + field + "' not found in one or more objects.");
                }
            }
            for (Map<String, Object> item : toSort) {
                Object fieldValueObject = item.get(field);

                // Ensure the field value is treated as an Integer
                if (!(fieldValueObject instanceof Integer)) {
                    throw new IllegalArgumentException("Field '" + field + "' must contain integers. Found: " + fieldValueObject);
                }

                // Cast the field value to Integer and add to the list
                Integer fieldValue = (Integer) fieldValueObject;
                fieldValuesList.add(fieldValue);
            }
            int[] fieldValues = fieldValuesList.stream().mapToInt(Integer::intValue).toArray();
            List<Result> results = sortingMadness.sort(fieldValues, iterations, order);
            logger.info("Collected field values: {}", Arrays.toString(fieldValues));
            logger.info("Finished sorting");

            // Create response in the specified format
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> resultMap = new HashMap<>();
            for (Result result : results) {
                logger.debug("algorithm: {}", result.getAlgorithm());
                logger.debug("timeElapsed [ns]: {}", result.getTime());
                logger.debug("sorted: {}", result.getSortedArray());
                Map<String, Object> algorithmResult = new HashMap<>();
                algorithmResult.put("timeElapsed [ns]", result.getTime());
                algorithmResult.put("sorted", result.getSortedArray());
                resultMap.put(result.getAlgorithm(), algorithmResult);
            }
            response.put("results", resultMap);
            logger.info("Results sent");
            // Return response as JSON
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            logger.error("Error processing input", e); // i don't know if it's necessary if throws show on log anyway
            return "{\"error\": \""+ e.getMessage()+"\"}";
        }
    }
}
