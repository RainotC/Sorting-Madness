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
 * The {@code SortingMadnessController} class is a REST controller that handles HTTP requests
 * for sorting operations using various algorithms. It accepts user-provided data and parameters,
 * sorts the data accordingly, and returns the sorted result along with performance metrics.
 * This class is designed to work with sorting tasks that involve multiple sorting algorithms
 * and can handle different types of data (integers or JSON objects).
 */
@RestController
@RequestMapping("/")  // Handle requests to the root
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    /**
     * Handles POST requests for sorting data. The input JSON must contain the following fields:
     * <ul>
     *     <li>{@code to-sort} - A list of integers or objects to be sorted. The data type depends on the presence of the {@code field} parameter.</li>
     *     <li>{@code algorithms} - A list of sorting algorithm names (e.g., BubbleSort, QuickSort).</li>
     *     <li>{@code iterations} - The maximum number of iterations to perform during sorting.</li>
     *     <li>{@code order} - The sorting order (either "ascending" or "descending").</li>
     *     <li>{@code time-limit} - The time limit in nanoseconds for the sorting operations.</li>
     * </ul>
     * Optionally, the {@code field} parameter can be included if sorting JSON objects based on a specific field (e.g., sorting objects by an "age" field).
     *
     * Example request body for sorting integers:
     * <pre>
     * {
     *     "to-sort": [5, 3, 8, 6, 2],
     *     "algorithms": ["BubbleSort", "QuickSort"],
     *     "iterations": 100,
     *     "order": "ascending",
     *     "time-limit": 1000000
     * }
     * </pre>
     *
     * Example request body for sorting JSON objects:
     * <pre>
     * {
     *     "to-sort": [{"name": "Alice", "age": 30}, {"name": "Bob", "age": 25}],
     *     "algorithms": ["QuickSort"],
     *     "iterations": 50,
     *     "order": "ascending",
     *     "field": "age",
     *     "time-limit": 1000000
     * }
     * </pre>
     *
     * @param jsonMap a map containing the input parameters as a JSON object
     * @return a JSON response containing the sorted data and time elapsed for each algorithm
     *         or an error message if the input is invalid
     */
    @PostMapping(produces = "application/json")
    public String post(@RequestBody Map<String, Object> jsonMap) {
        logger.info("Connection request received");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!jsonMap.containsKey("algorithms") || !jsonMap.containsKey("iterations") || !jsonMap.containsKey("order") || !jsonMap.containsKey("time-limit")) {
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
                    if (toSortInt == null) {
                        logger.debug("No 'to-sort' -- sorting random array");
                        toSortInt = generateRandomArray();
                    }
                }

            } catch (IllegalArgumentException | ClassCastException e) {
                logger.error("Invalid data types in input", e);

                return createErrorResponse(objectMapper, "Invalid data types in input: " + e.getMessage());
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
            // Create response with sorted results and elapsed time
            return createSortedResponse(objectMapper, results, toSort, field, containsString);
        } catch (Exception e) {
            logger.error("Error processing input", e);
            return createErrorResponse(new ObjectMapper(), "Error: " + e.getMessage());
        }
    }

    /**
     * Generates an array of random integers.
     * <p>
     * The size of the array is randomly chosen between 2 and 100 (inclusive).
     * Each element in the array is a random integer between 0 and 1000 (inclusive).
     * </p>
     *
     * @return an array of random integers with a randomly determined size
     */
    private static int[] generateRandomArray() {
        Random random = new Random();

        int minSize = 2, maxSize = 100;
        int size = random.nextInt(maxSize - minSize + 1) + minSize;
        int[] array = new int[size];

        int min = 0, max = 1000;
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min; // Generates numbers in range [min, max]
        }

        return array;
    }

    /**
     * Creates a JSON response with the error message.
     *
     * @param objectMapper the Jackson ObjectMapper instance
     * @param message the error message
     * @return a JSON string with the error message
     */
    private String createErrorResponse(ObjectMapper objectMapper, String message) {
        try {
            ObjectNode errorResponse = objectMapper.createObjectNode();
            errorResponse.put("error", message);
            return objectMapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing error response", e);
            return "{\"error\": \"An unexpected error occurred while processing the request.\"}";
        }
    }

    /**
     * Creates a JSON response containing the sorted results and performance metrics.
     *
     * @param objectMapper the Jackson ObjectMapper instance
     * @param results the list of sorting results
     * @param toSort the original data to be sorted
     * @param field the field to sort by (if applicable)
     * @param containsString whether the field contains strings
     * @return a JSON string with the sorted data and performance metrics
     */
    private String createSortedResponse(ObjectMapper objectMapper, List<Result> results, List<Map<String, Object>> toSort,
                                        String field, boolean containsString) throws JsonProcessingException {
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

                Map<String, Object> algorithmResult = new HashMap<>();
                algorithmResult.put("timeElapsed [ns]", result.getTime());
                algorithmResult.put("sorted", sortedToSort);
                resultMap.put(result.getAlgorithm(), algorithmResult);
            } else {
                Map<String, Object> algorithmResult = new HashMap<>();
                algorithmResult.put("timeElapsed [ns]", result.getTime());
                algorithmResult.put("sorted", result.getSortedArray());
                resultMap.put(result.getAlgorithm(), algorithmResult);
            }
        }
        response.put("results", resultMap);
        logger.info("Results sent");
        return objectMapper.writeValueAsString(response);
    }
}
