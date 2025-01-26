package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.sorting.logic.Result;
import pl.put.poznan.sorting.logic.SortingMadness;

import java.util.*;

import static java.util.Arrays.sort;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class SortingMadnessControllerTest {


    @Test
    void validInput() throws JsonProcessingException {
        int[] toSort = new int[]{1, 7, 3, 4, 5, 10, 500, 2, 50, 1000, 100, 10000, 100000, 1000000, 10000000, 100000000};
        int[] expected = toSort.clone();
        sort(expected);

        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        when(mockMap.get("iterations")).thenReturn(1000);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(1000);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Quick Sort", "Merge Sort"});
        when(mockMap.get("to-sort")).thenReturn(toSort);
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertTrue(response.contains("\"results\":"));
        assertTrue(response.contains("\"Merge Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"Quick Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"timeElapsed [ns]\""));

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedArrayJson = objectMapper.writeValueAsString(expected);
        assertTrue(response.contains(expectedArrayJson));
    }

    @Test
    void validInputNegativeIterations() throws JsonProcessingException {
        int[] toSort = new int[]{1, 7, 3, 4, 5, 10, 500, 2, 50, 1000, 100, 10000, 100000, 1000000, 10000000, 100000000};
        int[] expected = toSort.clone();
        sort(expected);


        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        when(mockMap.get("iterations")).thenReturn(-1);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(1000);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Quick Sort", "Merge Sort"});
        when(mockMap.get("to-sort")).thenReturn(toSort);
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertTrue(response.contains("\"results\":"));
        assertTrue(response.contains("\"Merge Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"Quick Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"timeElapsed [ns]\""));

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedArrayJson = objectMapper.writeValueAsString(expected);
        assertTrue(response.contains(expectedArrayJson));


    }

    @Test
    void validInputNegativeTimeLimit() throws JsonProcessingException {
        int[] toSort = new int[]{1, 7, 3, 4, 5, 10, 500, 2, 50, 1000, 100, 10000, 100000, 1000000, 10000000, 100000000};
        int[] expected = toSort.clone();
        sort(expected);

        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        when(mockMap.get("iterations")).thenReturn(10000);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(-1);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Quick Sort", "Merge Sort"});
        when(mockMap.get("to-sort")).thenReturn(toSort);
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertTrue(response.contains("\"results\":"));
        assertTrue(response.contains("\"Merge Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"Quick Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"timeElapsed [ns]\""));

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedArrayJson = objectMapper.writeValueAsString(expected);
        assertTrue(response.contains(expectedArrayJson));
    }


    @Test
    void validInputJSONObjects() {

        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        List<Map<String, Object>> toSort = new ArrayList<>();
        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("name", "Alice");
        obj1.put("age", 30);
        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("name", "Bob");
        obj2.put("age", 25);
        toSort.add(obj1);
        toSort.add(obj2);

        when(mockMap.get("to-sort")).thenReturn(toSort);
        when(mockMap.get("iterations")).thenReturn(1000);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(1000);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Merge Sort"});
        when(mockMap.get("field")).thenReturn("age");
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertTrue(response.contains("{\"results\":{\"Merge Sort\":{\"sorted\":[{\"name\":\"Bob\",\"age\":25},{\"name\":\"Alice\",\"age\":30}]"));
    }

    @Test
    void validInputNoArray() {

        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        when(mockMap.get("iterations")).thenReturn(1000);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(1000);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Quick Sort", "Merge Sort", "Bubble Sort"});
        when(mockMap.get("to-sort")).thenReturn(null);
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertTrue(response.contains("\"results\":"));
        assertTrue(response.contains("\"Merge Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"Quick Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"Bubble Sort\":{\"sorted\":"));
        assertTrue(response.contains("\"timeElapsed [ns]\""));
    }


    @Test
    void invalidInputNoInterations() {
        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(false);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertEquals("{\"error\":\"Error: Missing required fields: 'iterations', 'order', time-limit\"}", response);

    }

    @Test
    void invalidInputNoOrder() {
        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(false);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertEquals("{\"error\":\"Error: Missing required fields: 'iterations', 'order', time-limit\"}", response);

    }

    @Test
    void invalidInputNoTimeLimit() {
        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(false);

        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertEquals("{\"error\":\"Error: Missing required fields: 'iterations', 'order', time-limit\"}", response);

    }

    @Test
    void invalidInputJSONs(){
        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        List<Map<String, Object>> toSort = new ArrayList<>();
        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("name", "Alice");
        obj1.put("age", 30);
        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("name", "Bob");
        obj2.put("time", 25);
        toSort.add(obj1);
        toSort.add(obj2);

        when(mockMap.get("to-sort")).thenReturn(toSort);
        when(mockMap.get("iterations")).thenReturn(1000);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(1000);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Merge Sort"});
        when(mockMap.get("field")).thenReturn("age");
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertEquals("{\"error\":\"Error: Field 'age' not found in one or more objects.\"}", response);
    }

    @Test
    void invalidInputJSONFloat(){

        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(true);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        List<Map<String, Object>> toSort = new ArrayList<>();
        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("name", "Alice");
        obj1.put("age", 30.3);
        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("name", "Bob");
        obj2.put("age", 25);
        toSort.add(obj1);
        toSort.add(obj2);

        when(mockMap.get("to-sort")).thenReturn(toSort);
        when(mockMap.get("iterations")).thenReturn(1000);
        when(mockMap.get("order")).thenReturn("asc");
        when(mockMap.get("time-limit")).thenReturn(1000);
        when(mockMap.get("algorithms")).thenReturn(new String[]{"Merge Sort"});
        when(mockMap.get("field")).thenReturn("age");
        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertEquals("{\"error\":\"Error: Field 'age' must contain integers or strings. Found: 30.3\"}", response);
    }
}

