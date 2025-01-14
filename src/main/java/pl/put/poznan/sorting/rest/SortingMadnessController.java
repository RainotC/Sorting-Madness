package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.Result;
import pl.put.poznan.sorting.logic.SortingMadness;
import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

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
    @PostMapping(produces = "application/json")  // This is a GET request
    public String post(@RequestBody Map<String, Object> jsonMap) {  // Extract JSON from body
        logger.info("Connenction request recieved");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!jsonMap.containsKey("to-sort") || !jsonMap.containsKey("algorithms") ||
                    !jsonMap.containsKey("iterations") || !jsonMap.containsKey("order") || !jsonMap.containsKey("time-limit")) {
                throw new IllegalArgumentException("Missing required fields: 'to-sort', 'algorithms', 'iterations', 'order', time-limit");
            }

            // init for all data in a bigger JSON
            String field;
            field = null;
            List<Map<String, Object>> toSort = Collections.emptyList();
            int[] toSortInt = new int[0];
            List<Result> results = Collections.emptyList();;
            String[] algorithms;
            int iterations;
            String order;
            long timeLimit;

            try {
                //get all basic data
                algorithms = objectMapper.convertValue(jsonMap.get("algorithms"), String[].class);
                iterations = (int) jsonMap.get("iterations");
                order = (String) jsonMap.get("order");
                timeLimit = ((Number) jsonMap.get("time-limit")).longValue();
                if (algorithms.length<1)throw new IllegalArgumentException("List of algorithms cannot be empty");
                //check for field
                try {
                    field = (String) jsonMap.get("field");
                } catch (Exception e) {
                    logger.debug("No 'field' -- sorting a simple list");
                }
                // make toSort(Int) lists filled
                if (field!=null) {
                    logger.debug("'field' involved -- sorting JSON objects");
                    toSort = objectMapper.convertValue(jsonMap.get("to-sort"), new TypeReference<List<Map<String, Object>>>() {});
                } else {
                    toSortInt = objectMapper.convertValue(jsonMap.get("to-sort"), int[].class);
                }
            } catch(IllegalArgumentException | ClassCastException e){
                logger.error("Invalid data types in input", e); // i don't know if it's necessary if throws show on log anyway
                return "{\"error\": \"Invalid data types in input: "+e.getMessage()+"\"}"; //showing to client
            }
            // Log received parameters
            logger.debug("algorithms: {}", (Object) algorithms);
            logger.debug("iterations: {}", iterations);
            logger.debug("order: {}", order);
            logger.debug("time-limit: {}", timeLimit);
            logger.info("Started sorting");
            if (field != null) {
                SortingMadness sortingMadness = new SortingMadness(algorithms);
                //begin field
                List<Integer> fieldValuesList = new ArrayList<>();
                for (Map<String, Object> item : toSort) {
                    if (!item.containsKey(field)) {
                        throw new IllegalArgumentException("Field '" + field + "' not found in one or more objects.");
                    }
                }
                for (Map<String, Object> item : toSort) {
                    Object fieldValueObject = item.get(field);
                    if (!(fieldValueObject instanceof Integer)) {
                        throw new IllegalArgumentException("Field '" + field + "' must contain integers. Found: " + fieldValueObject);
                    }
                    Integer fieldValue = (Integer) fieldValueObject;
                    fieldValuesList.add(fieldValue);
                }
                int[] fieldValues = fieldValuesList.stream().mapToInt(Integer::intValue).toArray();
                results = sortingMadness.sort(fieldValues, iterations, order, timeLimit);

                logger.info("Collected field values: {}", Arrays.toString(fieldValues));
                logger.info("Finished sorting");
            } else {
                SortingMadness sortingMadness = new SortingMadness(algorithms);
                results = sortingMadness.sort(toSortInt, iterations, order, timeLimit);
            }

            // end for object field
            // Create response in the specified format
            List<Map<String, Object>> sortedToSort = new ArrayList<>();
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> resultMap = new HashMap<>();


            for (Result result : results) {
                if (field != null) {
                    sortedToSort.clear();
                    for (int j = 0; j < result.getSortedArray().length; j++) {
                        for (Map<String, Object> item : toSort) {
                            if (item.containsKey(field) && item.get(field).equals(result.getSortedArray()[j]) && !sortedToSort.contains(item)) {
                                sortedToSort.add(item);
                                break;
                            }
                        }
                    }
                    logger.debug("algorithm: {}", result.getAlgorithm());
                    logger.debug("timeElapsed [ns]: {}", result.getTime());
                    logger.debug("sorted: {}", sortedToSort);
                    Map<String, Object> algorithmResult = new HashMap<>();
                    algorithmResult.put("timeElapsed [ns]", result.getTime());
                    algorithmResult.put("sorted", sortedToSort);
                    resultMap.put(result.getAlgorithm(), algorithmResult);
                } else {
                    logger.debug("algorithm: {}", result.getAlgorithm());
                    logger.debug("timeElapsed [ns]: {}", result.getTime());
                    logger.debug("result: {}", result.getSortedArray());
                    Map<String, Object> algorithmResult = new HashMap<>();
                    algorithmResult.put("timeElapsed [ns]", result.getTime());
                    algorithmResult.put("sorted", result.getSortedArray());
                    resultMap.put(result.getAlgorithm(), algorithmResult);
                }
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
