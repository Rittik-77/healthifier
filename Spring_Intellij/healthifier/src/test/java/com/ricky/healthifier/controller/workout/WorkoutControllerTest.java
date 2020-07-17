package com.ricky.healthifier.controller.workout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.service.workout.WorkoutService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WorkoutController.class)
@ActiveProfiles("test")
public class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutService workoutService;

    @Before
    public void setUp() throws Exception {

        Workout w1 = new Workout("abc", 56.7);
        Workout w2 = new Workout("xyz", 98.0);
        Mockito.when(workoutService.getAllWorkouts()).thenReturn(Arrays.asList(w1, w2));
    }

    @Test
    public void testGetAllWorkouts() throws Exception {

        // For converting to json
        ObjectMapper objectMapper = new ObjectMapper();

        // Prepare the expected response
        WorkoutVO w1 = new WorkoutVO("abc", 56.7);
        WorkoutVO w2 = new WorkoutVO("xyz", 98.0);
        String json = objectMapper.writeValueAsString(Arrays.asList(w1,w2));

        // Call the API to get the actual response
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/workouts")).andReturn();
        String res = result.getResponse().getContentAsString();

        // Assert the response
        Assert.assertEquals(json, res);
    }
}