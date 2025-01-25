package pl.put.poznan.sorting.rest;

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
    void invalidInputNoInterations() {
        Map mockMap = mock(Map.class);
        when(mockMap.containsKey("iterations")).thenReturn(false);
        when(mockMap.containsKey("order")).thenReturn(true);
        when(mockMap.containsKey("time-limit")).thenReturn(true);

        SortingMadnessController sortingMadnessController = new SortingMadnessController();
        String response = sortingMadnessController.post(mockMap);

        assertEquals("{\"error\":\"Error: Missing required fields: 'iterations', 'order', time-limit\"}", response);

    }
}