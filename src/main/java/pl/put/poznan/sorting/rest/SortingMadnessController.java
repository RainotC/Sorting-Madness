package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
     *     <li>{@code to-sort} - an array of integers or lists of objects to be sorted</li>
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
    @PostMapping(produces = "application/json")
    public String post(@RequestBody Map<String, Object> jsonMap) {
        logger.info("Connection request received");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!jsonMap.containsKey("to-sort") || !jsonMap.containsKey("algorithms") ||
                    !jsonMap.containsKey("iterations") || !jsonMap.containsKey("order") || !jsonMap.containsKey("time-limit")) {
                throw new IllegalArgumentException("Missing required fields: 'to-sort', 'algorithms', 'iterations', 'order', time-limit");
            }

            String field = null;
            List<Map<String, Object>> toSort = Collections.emptyList();
            int[] toSortInt = new int[0];
            List<Result> results = Collections.emptyList();
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
                
                if (field!=null) {
                    logger.debug("'field' involved -- sorting JSON objects");
                    toSort = objectMapper.convertValue(jsonMap.get("to-sort"), new TypeReference<List<Map<String, Object>>>() {});
                } else {
                    toSortInt = objectMapper.convertValue(jsonMap.get("to-sort"), int[].class);
                }
            } catch (IllegalArgumentException | ClassCastException e) {
                logger.error("Invalid data types in input", e);
                
                // Create a JSON object for the error response
                ObjectNode errorResponse = objectMapper.createObjectNode();
                errorResponse.put("error", "Invalid data types in input: " + e.getMessage());

                // Return the error response as a JSON string
                return objectMapper.writeValueAsString(errorResponse);
            }
            // Log received parameters
            logger.debug("algorithms: {}", (Object) algorithms);
            logger.debug("iterations: {}", iterations);
            logger.debug("order: {}", order);
            logger.debug("time-limit: {}", timeLimit);
            logger.info("Started sorting");

            boolean containsString = false;
            if (field != null) {
                SortingMadness sortingMadness = new SortingMadness(algorithms);
                List<Object> fieldValuesList = new ArrayList<>();
                for (Map<String, Object> item : toSort) {
                    if (!item.containsKey(field)) {
                        throw new IllegalArgumentException("Field '" + field + "' not found in one or more objects.");
                    }
                    Object fieldValueObject = item.get(field);
                    if (fieldValueObject instanceof Integer || fieldValueObject instanceof String) {
                        fieldValuesList.add(fieldValueObject);
                    } else {
                        throw new IllegalArgumentException("Field '" + field + "' must contain integers or strings. Found: " + fieldValueObject);
                    }
                }

                // Check if any element is a String
                for (Object obj : fieldValuesList) {
                    if (obj instanceof String) {
                        containsString = true;
                        break;
                    }
                }

                // Cast the list to String[] or int[] based on the check
                if (containsString) {
                    String[] stringArray = new String[fieldValuesList.size()];
                    for (int i = 0; i < fieldValuesList.size(); i++) {
                        stringArray[i] = fieldValuesList.get(i).toString();
                    }
                    results = sortingMadness.sort(stringArray, iterations, order, timeLimit);

                } else {
                    int[] intArray = new int[fieldValuesList.size()];
                    for (int i = 0; i < fieldValuesList.size(); i++) {
                        intArray[i] = (Integer) fieldValuesList.get(i); // Casting to Integer
                    }
                    results = sortingMadness.sort(intArray, iterations, order, timeLimit);
                }


                logger.info("Finished sorting");
            } else {
                SortingMadness sortingMadness = new SortingMadness(algorithms);
                results = sortingMadness.sort(toSortInt, iterations, order, timeLimit);
            }

            List<Map<String, Object>> sortedToSort = new ArrayList<>();
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> resultMap = new HashMap<>();

            for (Result result : results) {
                logger.debug("algorithm: {}", result.getAlgorithm());
                logger.debug("timeElapsed [ns]: {}", result.getTime());

                if (field != null) {
                    sortedToSort.clear();
                    if (containsString) {
                        for (int j = 0; j < result.getSortedStringArray().length; j++) {
                            for (Map<String, Object> item : toSort) {
                                if (item.containsKey(field) && String.valueOf(item.get(field)).equals(result.getSortedStringArray()[j]) && !sortedToSort.contains(item)) {
                                    sortedToSort.add(item);
                                    break;
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j < result.getSortedArray().length; j++) {
                            for (Map<String, Object> item : toSort) {
                                if (item.containsKey(field) && item.get(field).equals(result.getSortedArray()[j]) && !sortedToSort.contains(item)) {
                                    sortedToSort.add(item);
                                    break;
                                }
                            }
                        }
                    }


                    logger.debug("sorted: {}", sortedToSort);
                    Map<String, Object> algorithmResult = new HashMap<>();
                    algorithmResult.put("timeElapsed [ns]", result.getTime());
                    algorithmResult.put("sorted", sortedToSort);
                    resultMap.put(result.getAlgorithm(), algorithmResult);
                } else {
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
            logger.error("Error processing input", e);
            try {
                // Create a JSON object for the error response
                ObjectNode errorResponse = objectMapper.createObjectNode();
                errorResponse.put("error", "Error: " + e.getMessage());

                // Return the error response as a JSON string
                return objectMapper.writeValueAsString(errorResponse);
            } catch (JsonProcessingException jsonException) {
                // Handle any unexpected JSON serialization errors
                logger.error("Error serializing error response", jsonException);
                return "{\"error\": \"An unexpected error occurred while processing the request.\"}";
            }
        }
    }
}
